package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Update the internal structure of a class.
 * Updates all parts from their base classifiers, and allows creation of all missing bases.
 * It is also possible to reference an existing classifier.
 */
@objid ("be375f38-0dd7-4e7a-9b09-b66b4f1f4ea8")
public class UpdateInternalStructure extends DefaultModuleCommandHandler {
    @objid ("5b3d8263-a3e4-4b64-934b-2f98d4e0e8b7")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
         
        try (ITransaction transaction = session.createTransaction("UpdateInternalStructure")) {
            InstanceUpdater p = new InstanceUpdater();
            p.updateInternalStructure(session, (Class) selectedElements.get(0));
        
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
    @objid ("579d70d5-21ac-48f1-95a4-3ca8dcb516e8")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        return ((selectedElements.size() > 0) && (!(selectedElements.get(0) instanceof ModuleComponent)));
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("7e23f58c-09d7-4ae8-ba6e-6a40f858f69e")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
