package com.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class Utilities_Upload {
	/**
	 * Phương thức tải ảnh lên hệ thống <br >
	 * 13/07/2020
	 * 
	 * @param request
	 * @param param_name
	 * @param UPLOAD_DIR
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public static String uploadFile(HttpServletRequest request, String param_name, String UPLOAD_DIR)
			throws IOException, ServletException {

		String fileName = "";
		String basePath = "";
		Part filePart = request.getPart(param_name);
		if (filePart != null) {// Nếu tên tham số tồn tại thì tiếp tục

			fileName = (String) getFileName(filePart);

			if (fileName != null && !fileName.equalsIgnoreCase("")) {

				if (fileName.indexOf(".jpg") != -1 || fileName.indexOf(".png") != -1) {

					String applicationPath = request.getServletContext().getRealPath("");
					System.out.println(applicationPath);
					basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
					System.out.println(basePath);
					InputStream inputStream = null;
					OutputStream outputStream = null;
					try {
						File outputFilePath = new File(basePath + fileName);
						inputStream = filePart.getInputStream();
						
						outputStream = new FileOutputStream(outputFilePath);
						int read = 0;
						final byte[] bytes = new byte[1024];
						while ((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}

					} catch (IOException e) {
						e.printStackTrace();
						fileName = "";
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						if (outputStream != null) {
							outputStream.close();
						}
					}
				} else {
					fileName = "";
				}
			}
		}

		// System.out.println("Tên file: "+fileName);
		
		return fileName;
	}

	private static String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		System.out.println("*****partHeader :" + partHeader);
		// System.out.println(part.getContentType());
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}

		return null;
	}

	/**
	 * Kiểm tra file nhị phân
	 * 
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean isBinaryFile(File f) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(f);
		int size = in.available();
		if (size > 1024) {
			size = 1024;
		}
		byte[] data = new byte[size];
		in.read(data);
		in.close();

		int ascii = 0;
		int other = 0;

		for (int i = 0; i < data.length; i++) {
			byte b = data[i];
			if (b < 0x09) {
				return true;
			}

			if (b == 0x09 || b == 0x0A || b == 0x0C || b == 0x0D) {
				ascii++;
			} else if (b >= 0x20 && b <= 0x7E) {
				ascii++;
			} else {
				other++;
			}
		}

		if (other == 0) {
			return false;
		}

		return 100 * other / (ascii + other) > 95;
	}

}
