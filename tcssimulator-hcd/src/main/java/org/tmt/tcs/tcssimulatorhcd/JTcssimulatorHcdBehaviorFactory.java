package org.tmt.tcs.tcssimulatorhcd;

import akka.actor.typed.javadsl.ActorContext;
import csw.framework.javadsl.JComponentBehaviorFactory;
import csw.framework.javadsl.JComponentHandlers;
import csw.framework.models.JCswContext;
import csw.command.client.messages.TopLevelActorMessage;

public class JTcssimulatorHcdBehaviorFactory extends JComponentBehaviorFactory {

    @Override
    public JComponentHandlers jHandlers(ActorContext<TopLevelActorMessage> ctx,JCswContext cswCtx) {
        return new JTcssimulatorHcdHandlers(ctx, cswCtx);
    }

}
