package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.StateUpdater;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a bus state machine from a state having entry/exit points.
 */
@objid ("1fd1ffc3-48e2-4875-bff3-e293d2c307b7")
public class UpdateStateFromStateMachine extends DefaultModuleCommandHandler {
    @objid ("1dd26822-3bec-4cba-84c1-6474892c29a9")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        State selectedState = (State) selectedElements.get(0);
        
        IModelingSession session = module.getModuleContext().getModelingSession();
         
        try (ITransaction transaction = session.createTransaction("UpdateStateFromStateMachine")) {
            StateUpdater updater = new StateUpdater();
            updater.updateStateFromStateMachine(session, selectedState);
        
            transaction.commit();
        } catch (ModelerModuleException e) {
            MessageDialog.openError (Display.getDefault ().getActiveShell (),
                    I18nMessageService.getString ("Ui.Error.Title"),
                    e.getMessage());
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("2cb9829b-9cb6-43a3-bb6a-94e56da96e2e")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        
        if (selectedElements.size() > 0) {
            State selectedState = (State) selectedElements.get(0);
            StateMachine stateMachine = selectedState.getSubMachine();
            return (stateMachine != null);
        }
        return false;
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("cc53770e-e553-46cd-abcc-10ce797007cb")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
