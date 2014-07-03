package my.ftsm.spfk.common.vo;

import java.io.InputStream;
import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class DocumentDownloadVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;
	private StreamedContent file;
	private InputStream inputStream;
	private String mimeType;
	private String docType;
	private Long id;
	private byte[] input;
	private String thumbName;
	private boolean save;

	public DocumentDownloadVO() {
		super();
	}

	public DocumentDownloadVO(String fileName, StreamedContent file) {
		super();
		this.fileName = fileName;
		this.file = file;
	}

	public DocumentDownloadVO(String fileName, InputStream inputStream,
			String mimeType, String docType) {
		super();
		this.fileName = fileName;
		this.inputStream = inputStream;
		this.mimeType = mimeType;
		this.docType = docType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getInput() {
		return input;
	}

	public void setInput(byte[] input) {
		this.input = input;
	}

	public String getThumbName() {
		return thumbName;
	}

	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

}
