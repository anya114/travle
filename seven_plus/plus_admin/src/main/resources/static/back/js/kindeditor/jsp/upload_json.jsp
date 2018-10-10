<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%
	//获取文件保存路径
	String filePath = request.getParameter("filePath");
	//文件保存目录路径
	String savePath = request.getSession().getServletContext()
			.getRealPath("/upload/")
			+ "/" + filePath + "/";

	//文件保存目录URL
	String saveUrl = request.getContextPath() + "/upload/" + filePath
			+ "/";

	//保存数据库的路径url
	String fileUrl = "upload/" + filePath + "/";

	//定义允许上传的文件扩展名
	HashMap<String, String> extMap = new HashMap<String, String>();
	extMap.put("image", "gif,jpg,jpeg,png,bmp");
	extMap.put("flash", "swf,flv");
	extMap.put("media",
			"swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,ogg,mp4");
	extMap.put("file",
			"doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,apk,ios,,ogg,mp4");

	//最大文件大小
	long maxSize = 50000000;

	response.setContentType("text/html; charset=UTF-8");

	if (!ServletFileUpload.isMultipartContent(request)) {
		out.println(getError("请选择文件。"));
		return;
	}
	//检查目录
	File uploadDir = new File(savePath);
	if (!uploadDir.isDirectory()) {
		uploadDir.mkdirs();//目录不存在直接创建
	}
	//检查目录写权限
	if (!uploadDir.canWrite()) {
		out.println(getError("上传目录没有写权限。"));
		return;
	}

	String dirName = request.getParameter("dir");
	if (dirName == null) {
		dirName = "image";
	}
	if (!extMap.containsKey(dirName)) {
		out.println(getError("目录名不正确。"));
		return;
	}
	//创建文件夹
	savePath += dirName + "/";
	saveUrl += dirName + "/";
	File saveDirFile = new File(savePath);
	if (!saveDirFile.exists()) {
		saveDirFile.mkdirs();
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String ymd = sdf.format(new Date());
	savePath += ymd + "/";
	saveUrl += ymd + "/";
	File dirFile = new File(savePath);
	if (!dirFile.exists()) {
		dirFile.mkdirs();
	}

	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setHeaderEncoding("UTF-8");
	/* final HttpSession hs = request.getSession();
    upload.setProgressListener(new ProgressListener(){  
       public void update(long pBytesRead, long pContentLength, int pItems) {  
           hs.setAttribute("itemNum", pItems);  
           hs.setAttribute("readSize", pBytesRead);  
           hs.setAttribute("totalSize", pContentLength);  
           hs.setAttribute("show", pBytesRead+"/"+pContentLength+" byte");  
           hs.setAttribute("rate", Math.round(new Float(pBytesRead) / new Float(pContentLength)*100));  
       }  
    });  */
	List items = upload.parseRequest(request);
	Iterator itr = items.iterator();
	while (itr.hasNext()) {
		FileItem item = (FileItem) itr.next();
		String fileName = item.getName();
		long fileSize = item.getSize();
		if (!item.isFormField()) {
			//检查扩展名
			String fileExt = fileName.substring(
					fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String> asList(extMap.get(dirName).split(","))
					.contains(fileExt)) {
				out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
						+ extMap.get(dirName) + "格式。"));
				return;
			}
			//检查文件大小
			if (item.getSize() > maxSize) {
				out.println(getError("上传文件大小超过限制。"));
				return;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			//String newFileName = df.format(new Date()) + "_"
					//+ new Random().nextInt(1000) + "." + fileExt; 
			String newFileName = ObjectHelper.md5Hex(fileName.substring(0,fileName.lastIndexOf(".")))+"." + fileExt;
			try {
				File uploadedFile = new File(savePath, newFileName);
				item.write(uploadedFile);
			} catch (Exception e) {
				out.println(getError("上传文件失败。"));
				return;
			}

			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", saveUrl + newFileName);
			out.println(obj.toJSONString());
		}
	}
%>
<%!private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
%>