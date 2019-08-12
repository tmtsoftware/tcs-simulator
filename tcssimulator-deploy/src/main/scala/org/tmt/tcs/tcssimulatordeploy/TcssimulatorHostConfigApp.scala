package org.tmt.tcs.tcssimulatordeploy

import csw.framework.deploy.hostconfig.HostConfig

object TcssimulatorHostConfigApp extends App {

  HostConfig.start("tcssimulator-host-config-app", args)

}
