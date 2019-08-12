package org.tmt.tcs.tcssimulatorassembly;

import akka.actor.typed.javadsl.ActorContext;
import csw.command.client.messages.TopLevelActorMessage;
import csw.framework.javadsl.JComponentHandlers;
import csw.framework.models.JCswContext;
import csw.location.api.models.TrackingEvent;
import csw.logging.api.javadsl.ILogger;
import csw.params.commands.CommandResponse;
import csw.params.commands.ControlCommand;
import csw.params.commands.Result;
import csw.params.core.generics.Key;
import csw.params.core.generics.Parameter;
import csw.params.core.models.Struct;
import csw.params.javadsl.JKeyType;

import java.util.concurrent.CompletableFuture;

/**
 * Domain specific logic should be written in below handlers.
 * This handlers gets invoked when component receives messages/commands from other component/entity.
 * For example, if one component sends Submit(Setup(args)) command to TcssimulatorHcd,
 * This will be first validated in the supervisor and then forwarded to Component TLA which first invokes validateCommand hook
 * and if validation is successful, then onSubmit hook gets invoked.
 * You can find more information on this here : https://tmtsoftware.github.io/csw/commons/framework.html
 */
public class JTcssimulatorAssemblyHandlers extends JComponentHandlers {

    private final JCswContext cswCtx;
    private final ILogger log;

    JTcssimulatorAssemblyHandlers(ActorContext<TopLevelActorMessage> ctx,JCswContext cswCtx) {
        super(ctx, cswCtx);
        this.cswCtx = cswCtx;
        this.log = cswCtx.loggerFactory().getLogger(getClass());
    }

    @Override
    public CompletableFuture<Void> jInitialize() {
    log.info("Initializing tcsSimulator assembly...");
    return CompletableFuture.runAsync(() -> {

        });
    }

    @Override
    public CompletableFuture<Void> jOnShutdown() {
        return CompletableFuture.runAsync(() -> {

        });
    }

    @Override
    public void onLocationTrackingEvent(TrackingEvent trackingEvent) {

    }

    @Override
    public CommandResponse.ValidateCommandResponse validateCommand(ControlCommand controlCommand) {
        return new CommandResponse.Accepted(controlCommand.runId());
    }

    @Override
    public CommandResponse.SubmitResponse onSubmit(ControlCommand controlCommand) {
        Key<Struct> assemblyStatusStructKey = JKeyType.StructKey().make("assemblyStatusStruct");
        Key<String> assemblyNameKey = JKeyType.StringKey().make("assemblyName");
        Key<String> statusKey = JKeyType.StringKey().make("status");
        Key<String> descriptionKey = JKeyType.StringKey().make("description");

        log.info("keys made");

        Struct s1 = new Struct().madd(assemblyNameKey.set("IERS"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s2 = new Struct().madd(assemblyNameKey.set("Sequencer"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s3 = new Struct().madd(assemblyNameKey.set("SHS"), statusKey.set("Offline"), descriptionKey.set("The assembly is currently offline."));
        Struct s4 = new Struct().madd(assemblyNameKey.set("ENV"), statusKey.set("Degraded"), descriptionKey.set("Communiation lost.  Using last valid values."));
        Struct s5 = new Struct().madd(assemblyNameKey.set("Mount"), statusKey.set("Tracking"), descriptionKey.set(""));
        Struct s6 = new Struct().madd(assemblyNameKey.set("Enclosure"), statusKey.set("Slewing"), descriptionKey.set(""));
        Struct s7 = new Struct().madd(assemblyNameKey.set("M3CS"), statusKey.set("Faulted"), descriptionKey.set("M3 not responding to commands."));
        Struct s8 = new Struct().madd(assemblyNameKey.set("M2CS"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s9 = new Struct().madd(assemblyNameKey.set("M1CS"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s10 = new Struct().madd(assemblyNameKey.set("Pointing Kernel"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s11 = new Struct().madd(assemblyNameKey.set("AGW"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s12 = new Struct().madd(assemblyNameKey.set("Corrections Module"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s13 = new Struct().madd(assemblyNameKey.set("SAM"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s14 = new Struct().madd(assemblyNameKey.set("NFIRAOS"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s15 = new Struct().madd(assemblyNameKey.set("IRIS"), statusKey.set("Ready"), descriptionKey.set(""));
        Struct s16 = new Struct().madd(assemblyNameKey.set("APS"), statusKey.set("Shutdown"), descriptionKey.set("The assembly is shutdown."));
        Struct s17 = new Struct().madd(assemblyNameKey.set("PFS"), statusKey.set("Shutdown"), descriptionKey.set("The assembly is shutdown."));

        log.info("structs made");

        Parameter<Struct> p1 = assemblyStatusStructKey.set(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17);

        log.info("parameter made");

        Result result = new Result("templatedatasource").add(p1);

        log.info("result made = " + result);

        return new CommandResponse.CompletedWithResult(controlCommand.runId(), result);
    }

    @Override
    public void onOneway(ControlCommand controlCommand) {

    }

    @Override
    public void onGoOffline() {

    }

    @Override
    public void onGoOnline() {

    }
}
