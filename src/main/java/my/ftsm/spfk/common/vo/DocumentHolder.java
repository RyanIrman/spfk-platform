package my.ftsm.spfk.common.vo;



import java.io.InputStream;


public class DocumentHolder {

	private InputStream inputStream;
	private String fileName;
	private String mimeType;

	public DocumentHolder() {
	}

	public DocumentHolder(InputStream inputStream, String fileName,
			String mimeType) {
		super();
		this.inputStream = inputStream;
		this.fileName = fileName;
		this.mimeType = mimeType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
