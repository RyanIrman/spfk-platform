package my.ftsm.spfk.service;

import java.io.InputStream;

import my.ftsm.spfk.common.vo.DocumentHolder;

import org.primefaces.model.UploadedFile;

public interface IDocumentManagementService {

	String uploadFile(UploadedFile uploadedFile, String folderPath);

	String uploadFile(InputStream inputStream, String fileName,
			String mimeType, String folderPath, String code, boolean encrypt,
			String penyerahanId);

	DocumentHolder getFile(String path);

}
