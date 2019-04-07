package product.itcast.cn.a239;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

//import cn.edu.tsc.dazuoye.bean.NewsInfo;

import android.util.Xml;


	public class NewsInfoService {
		//解析服务器返回的xml信息 获取所有新闻数据实体.
		public static List<NewsInfo> getNewsInfos(InputStream is) {
			//获取XmlPullParser对象
			XmlPullParser parser = Xml.newPullParser();
			try {
				parser.setInput(is, "utf-8");
				//获取指针
				int type = parser.getEventType();
				List<NewsInfo> newsInfos = null;
				NewsInfo newsInfo = null;
				//type不是文档结束
				while (type != XmlPullParser.END_DOCUMENT) {
					switch (type) {
						case XmlPullParser.START_TAG: //开始标签
							//拿到标签名并判断
							if ("newslist".equals(parser.getName())) {
								newsInfos = new ArrayList<NewsInfo>();
							} else if ("news".equals(parser.getName())) {
								newsInfo = new NewsInfo();
							} else if ("title".equals(parser.getName())) {
								//获取解析器当前指向元素的下一个文本节点的值
								String title = parser.nextText();
								newsInfo.setTitle(title);
							} else if ("description".equals(parser.getName())) {
								String description = parser.nextText();
								newsInfo.setDescription(description);
							} else if ("comment".equals(parser.getName())) {
								String comment = parser.nextText();
								newsInfo.setComment(Long.parseLong(comment));
							} else if ("type".equals(parser.getName())) {
								String newsType = parser.nextText();
								newsInfo.setType(Integer.parseInt(newsType));
							} else if ("image".equals(parser.getName())) {
								String icon = parser.nextText();
								newsInfo.setIconPath(icon);
							}
							break;
						case XmlPullParser.END_TAG:   //标签结束
							if ("news".equals(parser.getName())) {
								newsInfos.add(newsInfo);
								newsInfo = null;
							}
							break;
					}
					type = parser.next();
				}
				return newsInfos;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
