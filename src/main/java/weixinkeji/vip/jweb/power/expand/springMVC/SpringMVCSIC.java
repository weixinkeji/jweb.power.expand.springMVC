package weixinkeji.vip.jweb.power.expand.springMVC;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import weixinkeji.vip.jweb.power.config.JWPSystemInterfaceConfig;

/**
 * 示例
 * <p>
 * JWebPower框架 对接SrpingMVC框架 实例类
 * </p>
 * 
 * @author wangchunzi
 *
 */
public class SpringMVCSIC implements JWPSystemInterfaceConfig{

	@Override
	public String getURLByClass(Class<?> c) {
		return getUrlMapping(c);
	}

	@Override
	public String getURLByMethod(Method method) {
		return getUrlMapping(method);
	}

	@Override
	public String[] getRequestUrlSuffix() {
		return new String[] { ".json", ".xml" };
	}
	//取得SpringMVC 注解在类上的路径
	private String getUrlMapping(Class<?> c) {
		RequestMapping request = c.getAnnotation(RequestMapping.class);
		GetMapping get = c.getAnnotation(GetMapping.class);
		PostMapping post = c.getAnnotation(PostMapping.class);
		PutMapping put = c.getAnnotation(PutMapping.class);
		DeleteMapping delete = c.getAnnotation(DeleteMapping.class);
		PatchMapping patch = c.getAnnotation(PatchMapping.class);
		return this.getSpringMVCUrl(request, get, post, put, delete, patch);
	}
	//取得SpringMVC 注解在方法上的路径
	private String getUrlMapping(Method method) {
		RequestMapping request = method.getAnnotation(RequestMapping.class);
		GetMapping get = method.getAnnotation(GetMapping.class);
		PostMapping post = method.getAnnotation(PostMapping.class);
		PutMapping put = method.getAnnotation(PutMapping.class);
		DeleteMapping delete = method.getAnnotation(DeleteMapping.class);
		PatchMapping patch = method.getAnnotation(PatchMapping.class);
		return this.getSpringMVCUrl(request, get, post, put, delete, patch);
	}

	//取得SpringMVC 注解在类或方法上的路径
	private String getSpringMVCUrl(RequestMapping request, GetMapping get, PostMapping post, PutMapping put,
			DeleteMapping delete, PatchMapping patch) {
		String url = null;
		if (null != request) {
			url = this.getUrlByMapping(request);
		} else if (null != get) {
			url = this.getUrlByMapping(get);
		} else if (null != post) {
			url = this.getUrlByMapping(post);
		} else if (null != put) {
			url = this.getUrlByMapping(put);
		} else if (null != delete) {
			url = this.getUrlByMapping(delete);
		} else if (null != patch) {
			url = this.getUrlByMapping(patch);
		}
		return this.toFormatUrl(url);
	}

	/**
	 * 变成以/开头的路径
	 * 
	 * @param url
	 * @return
	 */
	private String toFormatUrl(String url) {
		if (null == url) {
			return null;
		}
		url = url.trim();
		if (url.isEmpty()) {
			return "";
		}
		if (url.equalsIgnoreCase("/")) {
			return "/";
		}
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		if (!url.startsWith("/")) {
			url = "/" + url;
		}
		return url;
	}

//========== 解析SpringMVC注解的标签========================
	private String getUrlByMapping(PatchMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return a.name();
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return a.value()[0];
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return a.path()[0];
		}
		return "";
	}

	private String getUrlByMapping(DeleteMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return a.name();
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return a.value()[0];
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return a.path()[0];
		}
		return "";
	}

	private String getUrlByMapping(PutMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return a.name();
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return a.value()[0];
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return a.path()[0];
		}
		return "";
	}

	private String getUrlByMapping(PostMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return a.name();
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return a.value()[0];
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return a.path()[0];
		}
		return "";
	}

	private String getUrlByMapping(GetMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return a.name();
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return a.value()[0];
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return a.path()[0];
		}
		return "";
	}

	private String getUrlByMapping(RequestMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return a.name();
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return a.value()[0];
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return a.path()[0];
		}
		return "";
	}
}
