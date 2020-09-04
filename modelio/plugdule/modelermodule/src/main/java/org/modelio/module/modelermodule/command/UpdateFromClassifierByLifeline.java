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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Update a lifeline's represented instance contents from its base classifier.
 * Allows creation of the instance, and of a new classifier if no base exists, or referencing an existing classifier.
 */
@objid ("e46ab28c-74ad-44b6-99c5-09063f8cf381")
public class UpdateFromClassifierByLifeline extends DefaultModuleCommandHandler {
    @objid ("82d4e47e-bdf4-4df9-a2f1-df89c5887ac3")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("UpdateFromClassifierByLifeline")) {
            Lifeline ll = (Lifeline) selectedElements.get(0);
        
            InstanceUpdater p = new InstanceUpdater();
            Instance inst = ll.getRepresented();
        
            if (inst != null) {
                p.updatePartFromInstanciedClassifier(session, inst);
            } else {
                p.updateInstanceAndClassifier(session, ll);
            }
            
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
    @objid ("4fb04da2-4efb-4619-9dda-d42bfe18f2a0")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        return selectedElements.size() > 0;
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("dad5376b-b256-4ba6-a902-4dcc2b548b79")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
