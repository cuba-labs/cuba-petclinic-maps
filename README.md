# CUBA Petclinic: Maps

<p align="center">
  <img src="https://github.com/cuba-platform/cuba-petclinic/blob/master/modules/web/themes/hover/branding/petclinic_logo_full.png"/>
</p>

This is a variation of [CUBA Petclinic](https://github.com/cuba-platform/cuba-petclinic) application working with the [CUBA Maps add-on](https://www.cuba-platform.com/marketplace/maps/).

## Domain model


![CUBA Petclinic Domain model](https://github.com/glebshalyganov/cuba-petclinic-maps/blob/master/img/domain-model-extended.png)


## Starting the application

#### start database
```
./start-db.sh
```

#### start application
```
./restart-server.sh
```

## Populating the application with the data

To see the nice view on maps in the application, you need to import the predefined data to it.

 * Find `import1.groovy` and `import2.groovy` scripts in the `/data` folder.

 * Put them into `conf/petclinic-core` folder on your application server.
 
 * In the application go to `Administration -> JMX Console`. Find `ScriptingManager` JMX bean.
 
 * Run `import1.groovy` script and then `import2.groovy` script.





