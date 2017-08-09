# Spring Cache

## 1 Integrate with Ehcache

### 注解详解

- @Cacheable: 缓存某个方法或某类中所有方法的返回值
	- value: 缓存的名称，需在spring配置文件中定义
	- key: 缓存的 key，默认为""，支持SpEL表达式
	- condition: 默认为""，支持SpEL表达式判断缓存条件
- @CacheEvict: 当方法调用时，清理指定的缓存
	- value: 缓存的名称
	- key: 缓存的key
	- condition: 条件判断
	- allEntries: 是否清空所有缓存，默认false
	- beforeInvocation: 是否在方法执行前就清空，默认false
- @CachePut: 方法每次都会被调用，然后将结果存于缓存
	- value: 缓存的名称
	- key: 缓存的key
	- condition: 条件判断

@Cacheable与@CachePut的区别在于使用相同的key调用该方法时，@Cacheable会忽略方法执行，而@CachePut每次都会调用方法，然后将结果缓存。@Cacheable从缓存中读取数据，因此，如果使用相同的key，@Cacheable可以读取到@CachePut更新后的缓存的值。  
