/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mfw.acegi.framework;

import java.io.IOException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * FactoryBean that creates a named EHCache {@link net.sf.ehcache.Cache}
 * instance (or a decorator that implements the {@link net.sf.ehcache.Ehcache}
 * interface), representing a cache region within an EHCache
 * {@link net.sf.ehcache.CacheManager}.
 *
 * <p>
 * If the specified named cache is not configured in the cache configuration
 * descriptor, this FactoryBean will construct an instance of a Cache with the
 * provided name and the specified cache properties and add it to the
 * CacheManager for later retrieval. If some or all properties are not set at
 * configuration time, this FactoryBean will use defaults.
 *
 * <p>
 * Note: If the named Cache instance is found, the properties will be ignored
 * and the Cache instance will be retrieved from the CacheManager.
 *
 * <p>
 * Note: As of Spring 2.0, this FactoryBean is based on EHCache 1.2's API (in
 * particular the Ehcache interface and the extended Cache constructor). It is
 * not compatible with EHCache 1.1 anymore; please upgrade to EHCache 1.2.3 or
 * higher.
 *
 * @author Dmitriy Kopylenko
 * @author Juergen Hoeller
 * @since 1.1.1
 * @see #setCacheManager
 * @see EhCacheManagerFactoryBean
 * @see net.sf.ehcache.Cache
 */
public class EhCacheFactoryBean implements FactoryBean, BeanNameAware, InitializingBean {

	protected final Log logger = LogFactory.getLog(getClass());

	private CacheManager cacheManager;

	private String cacheName;

	private int maxElementsInMemory = 10000;

	private boolean overflowToDisk = true;

	private String diskStorePath;

	private boolean eternal = false;

	private int timeToLive = 120;

	private int timeToIdle = 120;

	private boolean diskPersistent = false;

	private int diskExpiryThreadIntervalSeconds = 120;

	private boolean blocking = false;

	private String beanName;

	private Cache cache;

	/**
	 * Set a CacheManager from which to retrieve a named Cache instance. By
	 * default, <code>CacheManager.getInstance()</code> will be called.
	 * <p>
	 * Note that in particular for persistent caches, it is advisable to
	 * properly handle the shutdown of the CacheManager: Set up a separate
	 * EhCacheManagerFactoryBean and pass a reference to this bean property.
	 * <p>
	 * A separate EhCacheManagerFactoryBean is also necessary for loading
	 * EHCache configuration from a non-default config location.
	 * 
	 * @see EhCacheManagerFactoryBean
	 * @see net.sf.ehcache.CacheManager#getInstance
	 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * Set a name for which to retrieve or create a cache instance. Default is
	 * the bean name of this EhCacheFactoryBean.
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	/**
	 * Specify the maximum number of cached objects in memory. Default is 10000
	 * elements.
	 */
	public void setMaxElementsInMemory(int maxElementsInMemory) {
		this.maxElementsInMemory = maxElementsInMemory;
	}

	/**
	 * Set whether elements can overflow to disk when the in-memory cache has
	 * reached the maximum size limit. Default is "true".
	 */
	public void setOverflowToDisk(boolean overflowToDisk) {
		this.overflowToDisk = overflowToDisk;
	}

	/**
	 * Set the location of temporary files for the disk store of this cache.
	 * Default is the CacheManager's disk store path.
	 */
	public void setDiskStorePath(String diskStorePath) {
		this.diskStorePath = diskStorePath;
	}

	/**
	 * Set whether elements are considered as eternal. If "true", timeouts are
	 * ignored and the element is never expired. Default is "false".
	 */
	public void setEternal(boolean eternal) {
		this.eternal = eternal;
	}

	/**
	 * Set t he time in seconds to live for an element before it expires, i.e.
	 * the maximum time between creation time and when an element expires. It is
	 * only used if the element is not eternal. Default is 120 seconds.
	 */
	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * Set the time in seconds to idle for an element before it expires, that
	 * is, the maximum amount of time between accesses before an element
	 * expires. This is only used if the element is not eternal. Default is 120
	 * seconds.
	 */
	public void setTimeToIdle(int timeToIdle) {
		this.timeToIdle = timeToIdle;
	}

	/**
	 * Set whether the disk store persists between restarts of the Virtual
	 * Machine. The default is "false".
	 */
	public void setDiskPersistent(boolean diskPersistent) {
		this.diskPersistent = diskPersistent;
	}

	/**
	 * Set the number of seconds between runs of the disk expiry thread. The
	 * default is 120 seconds.
	 */
	public void setDiskExpiryThreadIntervalSeconds(int diskExpiryThreadIntervalSeconds) {
		this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
	}

	/**
	 * Set whether to use a blocking cache that lets read attempts block until
	 * the requested element is created.
	 * <p>
	 * If you intend to build a self-populating blocking cache, consider
	 * specifying a {@link #setCacheEntryFactory CacheEntryFactory}.
	 * 
	 * @see net.sf.ehcache.constructs.blocking.BlockingCache
	 * @see #setCacheEntryFactory
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@Override
	public void afterPropertiesSet() throws CacheException, IOException {
		// If no CacheManager given, fetch the default.
		if (this.cacheManager == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Using default EHCache CacheManager for cache region '" + this.cacheName + "'");
			}
			this.cacheManager = CacheManager.getInstance();
		}

		// If no cache name given, use bean name as cache name.
		if (this.cacheName == null) {
			this.cacheName = this.beanName;
		}

		// Fetch cache region: If none with the given name exists,
		// create one on the fly.
		if (this.cacheManager.cacheExists(this.cacheName)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Using existing EHCache cache region '" + this.cacheName + "'");
			}
			this.cache = this.cacheManager.getCache(this.cacheName);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating new EHCache cache region '" + this.cacheName + "'");
			}
			Cache rawCache = createCache();
			this.cacheManager.addCache(rawCache);
			// Cache decoratedCache = decorateCache(rawCache);
			// this.cacheManager.replaceCacheWithDecoratedCache(rawCache,
			// decoratedCache);
			// this.cache = decoratedCache;
			this.cache = rawCache;
		}
	}

	/**
	 * Create a raw Cache object based on the configuration of this FactoryBean.
	 */
	private Cache createCache() {
		return new Cache(this.cacheName, this.maxElementsInMemory, this.overflowToDisk, this.eternal, this.timeToLive, this.timeToIdle,
				this.diskPersistent, this.diskExpiryThreadIntervalSeconds);
	}

	/**
	 * Decorate the given Cache, if necessary.
	 * <p>
	 * The default implementation simply returns the given cache object as-is.
	 * 
	 * @param cache
	 *            the raw Cache object, based on the configuration of this
	 *            FactoryBean
	 * @return the (potentially decorated) cache object to be registered with
	 *         the CacheManager
	 */
	/*
	 * protected Cache decorateCache(Cache cache) { if (this.cacheEntryFactory
	 * != null) { if (this.cacheEntryFactory instanceof
	 * UpdatingCacheEntryFactory) { return new
	 * UpdatingSelfPopulatingCache(cache, (UpdatingCacheEntryFactory)
	 * this.cacheEntryFactory); } else { return new SelfPopulatingCache(cache,
	 * this.cacheEntryFactory); } } if (this.blocking) { return new
	 * BlockingCache(cache); } return cache; }
	 */

	@Override
	public Object getObject() {
		return this.cache;
	}

	@Override
	public Class getObjectType() {
		return (this.cache != null ? this.cache.getClass() : Cache.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
