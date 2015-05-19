1. Put druid library jars under ```/usr/local/lib/druid```
2. Put druid node configuration under ```/usr/etc/druid_config```
3. Optinally, put ```realtime_spec``` under ```/usr/etc/druid_config/realtime_spec```

```
├── broker
│   └── runtime.properties
├── common_resource
│   ├── common.runtime.properties
│   └── log4j2.xml
├── coordinator
│   └── runtime.properties
├── historical
│   └── runtime.properties
├── overlord
└── realtime
    ├── runtime.properties
    ├── twitter4j.properties
    └── twitter_realtime.spec
```