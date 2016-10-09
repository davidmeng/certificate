package mfw.acegi.framework;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

/**
 * Caches <code>User</code> objects using a Spring IoC defined <A
 * HREF="http://ehcache.sourceforge.net">EHCACHE</a>.
 *
 * @author Ben Alex
 * @version $Id: EhCacheUserCache.java,v 1.1 2008/04/22 07:33:40 qinzhengming
 *          Exp $
 */
public class EhCacheBasedUserCache implements UserCache, InitializingBean {
	// ~ Static fields/initializers
	// =====================================================================================

	// ~ Instance fields
	// ================================================================================================

	private Cache cache;

	// ~ Methods
	// ========================================================================================================

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "cache mandatory");
	}

	public Cache getCache() {
		return cache;
	}

	@Override
	public UserDetails getUserFromCache(String username) {
		Element element = null;

		try {
			element = cache.get(username);
		} catch (CacheException cacheException) {
			throw new DataRetrievalFailureException("Cache failure: " + cacheException.getMessage());
		}

		if (element == null) {
			return null;
		} else {
			return (UserDetails) element.getValue();
		}
	}

	@Override
	public void putUserInCache(UserDetails user) {
		Element element = new Element(user.getUsername(), user);

		cache.put(element);
	}

	public void removeUserFromCache(UserDetails user) {

		this.removeUserFromCache(user.getUsername());
	}

	@Override
	public void removeUserFromCache(String username) {
		cache.remove(username);
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
}
