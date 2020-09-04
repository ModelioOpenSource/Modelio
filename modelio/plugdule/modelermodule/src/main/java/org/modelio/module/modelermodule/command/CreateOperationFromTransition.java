package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create an operation from a transition.
 * An Instance might be created in the process, or a Classifier.
 * @see InstanceUpdater#createInstanceAndClassifier(IModelingSession, Lifeline)
 */
@objid ("d841a44a-fe86-4e0e-8151-c2f458190919")
public class CreateOperationFromTransition extends DefaultModuleCommandHandler {
    @objid ("1f0746e8-11f4-4a9a-b18c-da8cc2fed947")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
                
        try (ITransaction transaction = session.createTransaction("CreateOperationFromTransition")) {
            InstanceUpdater p = new InstanceUpdater();
            p.createOperation(session, (Transition) selectedElements.get(0));
            
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
    @objid ("ea3a9c11-f111-4ac4-a3ae-1215eb1e7df8")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        if (selectedElements.size() > 0){
            for (MObject element : selectedElements) {
                return ((element instanceof Transition) 
                        && (((Transition) element).getProcessed() == null)) ;
            }
        }
        return false;
    }

    /**
     * This method precizes if a command has to be desactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("ea08963a-172a-455a-b4f3-2772e4752056")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
