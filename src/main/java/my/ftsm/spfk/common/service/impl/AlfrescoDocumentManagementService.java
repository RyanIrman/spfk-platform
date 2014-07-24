package my.ftsm.spfk.common.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









import my.ftsm.spfk.common.domain.RunningNumber;
import my.ftsm.spfk.common.infrastructure.CryptoUtil;
import my.ftsm.spfk.common.infrastructure.SystemPropertiesUtil;
import my.ftsm.spfk.common.service.IDocumentManagementService;
import my.ftsm.spfk.common.service.ISistemPengurusanService;
import my.ftsm.spfk.common.vo.DocumentHolder;

import org.alfresco.cmis.client.AlfrescoDocument;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlfrescoDocumentManagementService implements
		IDocumentManagementService, InitializingBean {

	private static Map<String, String> connectionParameters;

	@Autowired
	private ISistemPengurusanService runningNumberService;

	private SessionFactory factory;
	private Repository repository;

	static {
//
//		connectionParameters = new HashMap<>();
//		// user credentials
//		connectionParameters.put(SessionParameter.USER,
//				SystemPropertiesUtil.getStringProperty("alfresco.username"));
//		connectionParameters.put(SessionParameter.PASSWORD,
//				SystemPropertiesUtil.getStringProperty("alfresco.password"));
//
//		// connection settings
//		String ipAddress = SystemPropertiesUtil
//				.getStringProperty("alfresco.ip_address");
//		String port = SystemPropertiesUtil.getStringProperty("alfresco.port");
//		connectionParameters.put(SessionParameter.ATOMPUB_URL, "http://"
//				+ ipAddress + ":" + port + "/alfresco/cmisatom");
//		connectionParameters.put(SessionParameter.BINDING_TYPE,
//				BindingType.ATOMPUB.value());
//
//		// Set the alfresco object factory
//		connectionParameters.put(SessionParameter.OBJECT_FACTORY_CLASS,
//				"org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// default factory implementation
//		factory = SessionFactoryImpl.newInstance();
//
//		List<Repository> repositories = factory
//				.getRepositories(connectionParameters);
//
//		repository = repositories.get(0);

	}

	@Override
	public String uploadFile(UploadedFile uploadedFile, String folderPath) {

		String result = null;

		try {
			InputStream inputstream = uploadedFile.getInputstream();
			String contentType = uploadedFile.getContentType();
			String fileName = uploadedFile.getFileName();

			result = uploadFile(inputstream, fileName, contentType, folderPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public String uploadFile(InputStream inputStream, String fileName,
			String mimeType, String folderPath) {
		return this.uploadFile(inputStream, fileName, mimeType, folderPath,
				null, false, null);
	}

	@Override
	public String uploadFile(InputStream inputStream, String fileName,
			String mimeType, String folderPath, String code, boolean encrypt,
			String penyerahanId) {

		String result = null;

		if (StringUtils.isNotBlank(code)) {
			// Generate File Name by Running Number
			RunningNumber docRN = runningNumberService
					.findRunningNumberByCode(code);
			if (docRN == null) {
				docRN = new RunningNumber();
				docRN.setCode(code);
			}

			Long runningNumber = docRN.getRunningNumber() == null ? 0L : docRN
					.getRunningNumber();
			runningNumber = runningNumber + 1;
			docRN.setRunningNumber(runningNumber);

			docRN = runningNumberService.saveRunningNumber(docRN);

			fileName = docRN.getCode() + "_" + docRN.getRunningNumber();
		}

		if (encrypt) {
			try {
				byte[] encrypted = CryptoUtil.encrypt(IOUtils
						.toByteArray(inputStream));
				inputStream = new ByteArrayInputStream(encrypted);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Session session = repository.createSession();

		String rootFolder = SystemPropertiesUtil
				.getStringProperty("alfresco.root_folder");
		String folderPath2 = StringUtils
				.isBlank(folderPath) ? rootFolder : rootFolder + "/"
				+ folderPath;

		// create and get folders
		Folder folder = getFolder(folderPath2, session);

		createDocument(inputStream, fileName, mimeType, folder, session,
				folderPath, penyerahanId);

		result = getDocumentFullPath(folderPath, fileName);

		// String statement =
		// "SELECT d.* FROM cmis:document as d join cm:titled as t on d.cmis:objectId = t.cmis:objectId where t.cm:description = '261201300001'";
		//
		// ItemIterable<QueryResult> results = session.query(statement, false);
		//
		// for (QueryResult qResult : results) {
		// String objectId =
		// qResult.getPropertyValueById(PropertyIds.OBJECT_ID);
		// Document doc = (Document)
		// session.getObject(session.createObjectId(objectId));
		// System.out.println(doc.getName());
		// }

		return result;
	}

	private static String getDocumentFullPath(String folderPath, String fileName) {
		String rootFolder = SystemPropertiesUtil
				.getStringProperty("alfresco.root_folder");

		String result = "/" + rootFolder;

		return StringUtils.isBlank(folderPath) ? result
				+ "/" + fileName : result + "/" + folderPath + "/" + fileName;
	}

	private static void createDocument(InputStream inputStream,
			String fileName, String mimeType, Folder folder, Session session,
			String folderPath, String penyerahanId) {
		ContentStream contentStream = new ContentStreamImpl(fileName, null,
				mimeType, inputStream);

		String documentFullPath = getDocumentFullPath(folderPath, fileName);

		AlfrescoDocument document = getDocument(session, documentFullPath);

		if (document == null) {
			// create a major version
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.OBJECT_TYPE_ID,
					"cmis:document, P:cm:titled");
			properties.put(PropertyIds.NAME, fileName);
			properties.put("cm:description", penyerahanId);

			folder.createDocument(properties, contentStream,
					VersioningState.MAJOR);
		} else {
			// upgrade document
			document = (AlfrescoDocument) session
					.getObject(document.checkOut());
			document.checkIn(false, null, contentStream, "Updated");
		}
	}

	@Override
	public DocumentHolder getFile(String path) {

		DocumentHolder result = new DocumentHolder();

		Session session = repository.createSession();
		Document document = getDocument(session, path);

		String contentStreamMimeType = document.getContentStreamMimeType();
		String contentStreamFileName = document.getContentStreamFileName();
		InputStream stream = document.getContentStream().getStream();

		result.setFileName(contentStreamFileName);
		result.setInputStream(stream);
		result.setMimeType(contentStreamMimeType);

		return result;
	}

	private static AlfrescoDocument getDocument(Session session, String path) {

		AlfrescoDocument result = null;

		try {
			CmisObject object = session.getObjectByPath(path);
			result = (AlfrescoDocument) object;
		} catch (CmisObjectNotFoundException ex) {
		}

		return result;
	}

	private static Folder getFolder(String folderName, Session session) {

		Folder root = session.getRootFolder();

		String[] splitted = folderName.split("/");
		Folder childFolder = null;

		for (int i = 0; i < splitted.length; i++) {

			String split = splitted[i];

			if (i == 0) {
				childFolder = getChildFolderAndCreateIfNonExistent(root, split);
			} else {
				childFolder = getChildFolderAndCreateIfNonExistent(childFolder,
						split);
			}
		}

		return childFolder;
	}

	private static Folder getChildFolderAndCreateIfNonExistent(Folder folder,
			String folderName) {

		if (StringUtils.isBlank(folderName)) {
			return null;
		}

		Folder childFolder = getChildFolder(folder, folderName);

		if (childFolder == null) {
			// properties
			// (minimal set: name and object type id)
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
			properties.put(PropertyIds.NAME, folderName);

			// create the folder
			childFolder = folder.createFolder(properties);
		}

		return childFolder;
	}

	private static Folder getChildFolder(Folder folder, String folderName) {
		Folder result = null;

		ItemIterable<CmisObject> children = folder.getChildren();

		for (CmisObject item : children) {
			if (item instanceof Folder) {
				Folder f = (Folder) item;
				if (f.getName().equals(folderName)) {
					result = f;
					break;
				}
			}
		}

		return result;
	}

}