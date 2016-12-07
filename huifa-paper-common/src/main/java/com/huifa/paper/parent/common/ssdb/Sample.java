package com.huifa.paper.parent.common.ssdb;

public class Sample {
	private static SSDB ssdb;
	private static final String host = "139.224.63.184";
	private static final int port = 8888;
	static
	{
		try {
			ssdb = new SSDB(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		String docCheckId = "1443903061310";
		String content = "同时针对网络违法行为具有易实施、成本低廉、隐蔽性强和危害性广等特点,在强化立法和执法的同时,尝试建立一套如网上法院、网上仲裁、网络公证等法律服务与保障体系,以更加方便和快捷的方式防止和打击电子商务领域的非法经营和网络违法违规行为。";
		ssdb.setx(docCheckId, content,10000);
		String value = new String(ssdb.get(docCheckId));
		System.out.println(value);
//		ssdb.del(Constants.TOKEN);
//		ssdb.del("test_access_token");
//		System.out.println(content.endsWith(value));
	}
}
