package org.tmt.tcs.tcssimulatordeploy

import csw.framework.deploy.containercmd.ContainerCmd

object TcssimulatorContainerCmdApp extends App {

  ContainerCmd.start("tcssimulator-container-cmd-app", args)

}
