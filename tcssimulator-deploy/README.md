# tcssimulator-deploy

This module contains apps and configuration files for host deployment using 
HostConfig (https://tmtsoftware.github.io/csw/apps/hostconfig.html) and 
ContainerCmd (https://tmtsoftware.github.io/csw/framework/deploying-components.html).

An important part of making this work is ensuring the host config app (TcssimulatorHostConfigApp) is built
with all of the necessary dependencies of the components it may run.  This is done by adding settings to the
built.sbt file:

```
lazy val `tcssimulator-deploy` = project
  .dependsOn(
    `tcssimulator-assembly`,
    `tcssimulator-hcd`
  )
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Dependencies.TcssimulatorDeploy
  )
```

and in Libs.scala:

```

  val `csw-framework`  = "org.tmt" %% "csw-framework"  % Version

```

To start tcsSimulator Assembly and HCD, follow below steps:

 - Run `sbt tcssimulator-deploy/universal:packageBin`, this will create self contained zip in target/universal directory
 - Unzip generate zip and enter into bin directory
 - Run container cmd script or host config app script
 - Ex.  `./tcsSimulator-host-config-app --local ../../../../tcssimulator-deploy/src/main/resources/TcssimulatorHostConfig.conf -s ./tcsSimulator-container-cmd-app`

Note: the CSW Location Service must be running before starting the components.
See https://tmtsoftware.github.io/csw/apps/cswlocationserver.html .