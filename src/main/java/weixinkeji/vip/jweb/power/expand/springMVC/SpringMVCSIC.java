package weixinkeji.vip.jweb.power.expand.springMVC;

import java.lang.reflect.Method;

import org.springframework.stereotype.Controller;
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
public class SpringMVCSIC implements JWPSystemInterfaceConfig {

	/**
	 * 获取类绑定的URL地址
	 * <p>
	 * 此类不是控制类，请返回null
	 * <p>
	 * 如果此类没有绑定URL，但是方法绑定了，请返回空
	 * 
	 * @param c 绑定了相关注解的类
	 * @return String
	 */
	@Override
	public String getURLByClass(Class<?> c) {
		RequestMapping request = c.getAnnotation(RequestMapping.class);
		GetMapping get = c.getAnnotation(GetMapping.class);
		PostMapping post = c.getAnnotation(PostMapping.class);
		PutMapping put = c.getAnnotation(PutMapping.class);
		DeleteMapping delete = c.getAnnotation(DeleteMapping.class);
		PatchMapping patch = c.getAnnotation(PatchMapping.class);
		Controller controller=c.getAnnotation(Controller.class);
		
		
		if (null != request) {
			return this.getUrlByMapping(request);
		} else if (null != get) {
			return this.getUrlByMapping(get);
		} else if (null != post) {
			return this.getUrlByMapping(post);
		} else if (null != put) {
			return this.getUrlByMapping(put);
		} else if (null != delete) {
			return this.getUrlByMapping(delete);
		} else if (null != patch) {
			return this.getUrlByMapping(patch);
		}else if(null!=controller) {//没注有路径。但确实是控制类
			return "";
		}
		return null;
	}
	/**
	 * 获取方法绑定的URL地址
	 * 
	 * <p>
	 * 此方法不是控制方法，请返回null
	 * 
	 * @param method 绑定了相关url的方法
	 * @return String
	 */
	@Override
	public String getURLByMethod(Method method) {
		RequestMapping request = method.getAnnotation(RequestMapping.class);
		GetMapping get = method.getAnnotation(GetMapping.class);
		PostMapping post = method.getAnnotation(PostMapping.class);
		PutMapping put = method.getAnnotation(PutMapping.class);
		DeleteMapping delete = method.getAnnotation(DeleteMapping.class);
		PatchMapping patch = method.getAnnotation(PatchMapping.class);
		if (null != request) {
			return this.getUrlByMapping(request);
		} else if (null != get) {
			return this.getUrlByMapping(get);
		} else if (null != post) {
			return this.getUrlByMapping(post);
		} else if (null != put) {
			return this.getUrlByMapping(put);
		} else if (null != delete) {
			return this.getUrlByMapping(delete);
		} else if (null != patch) {
			return this.getUrlByMapping(patch);
		}
		return null;
	}

	/**
	 * 请求路径 默认后缀
	 * <p>
	 * 有些特别的框架，比如SpringMVC,他配置拦截的后缀没有。
	 * <p>
	 * 但针对指定数据格式请求的url却有后缀。比如.json后缀，.xml后缀（即，不是静态资源，却加了静态资源的后缀）
	 * 
	 * @return String[]
	 */
	@Override
	public String[] getRequestUrlSuffix() {
		return new String[] { ".json", ".xml" };
	}
	
	//从 注解PatchMapping 取得路径。并确保它们是/开头（没有，则强加）
	private String getUrlByMapping(PatchMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return formatUrl(a.name());
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return formatUrl(a.value()[0]);
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return formatUrl(a.path()[0]);
		}
		return "";
	}
	//从 注解DeleteMapping 取得路径。 并确保它们是/开头（没有，则强加）
	private String getUrlByMapping(DeleteMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return formatUrl(a.name());
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return formatUrl(a.value()[0]);
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return formatUrl(a.path()[0]);
		}
		return "";
	}
	//从 注解PutMapping 取得路径。 并确保它们是/开头（没有，则强加）
	private String getUrlByMapping(PutMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return formatUrl(a.name());
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return formatUrl(a.value()[0]);
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return formatUrl(a.path()[0]);
		}
		return "";
	}
	//从 注解PostMapping 取得路径。 并确保它们是/开头（没有，则强加）
	private String getUrlByMapping(PostMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return formatUrl(a.name());
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return formatUrl(a.value()[0]);
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return formatUrl(a.path()[0]);
		}
		return "";
	}
	//从 注解GetMapping 取得路径。 并确保它们是/开头（没有，则强加）
	private String getUrlByMapping(GetMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return formatUrl(a.name());
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return formatUrl(a.value()[0]);
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return formatUrl(a.path()[0]);
		}
		return "";
	}

	//从 注解RequestMapping 取得路径。 并确保它们是/开头（没有，则强加）
	private String getUrlByMapping(RequestMapping a) {
		if (null != a.name() && !a.name().isEmpty()) {
			return formatUrl(a.name());
		}
		if (a.value().length > 0 && !a.value()[0].isEmpty()) {
			return formatUrl(a.value()[0]);
		}
		if (a.path().length > 0 && !a.path()[0].isEmpty()) {
			return formatUrl(a.path()[0]);
		}
		return "";
	}

	//确保它们是/开头（没有，则强加）
	private String formatUrl(String url) {
		if (!url.startsWith("/")) {
			return "/" + url;
		}
		return url;
	}

}
