package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a classifier from an instance.
 * Includes creation of:
 * - ports from the instance ports.
 * - attributes from attribute links.
 * - operations from incoming messages.
 */
@objid ("f866f251-cbd5-4c34-ba27-ee442e9d5659")
public class CreateClassifier extends DefaultModuleCommandHandler {
    @objid ("fa39ac70-7b17-4f33-925b-dd26a733345f")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("CreateClassifierByLifeline")) {
            Instance inst = (Instance) selectedElements.get(0);
        
            InstanceUpdater p = new InstanceUpdater();
            p.createClassifier(session, inst);
            
            transaction.commit();
        }catch (ModelerModuleException e) {
            MessageDialog.openError (Display.getDefault ().getActiveShell (),
                                     I18nMessageService.getString ("Ui.Error.Title"),
                                     e.getMessage());
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("8e9016f3-a760-4ba5-9556-3ea65cbdc073")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        // Gray the command if the instance already has a base class
        for (MObject element : selectedElements) {
            if (element instanceof Instance) {
                if (((Instance) element).getBase() != null) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return selectedElements.size() > 0 && !((Instance) selectedElements.get(0) instanceof Port);
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("c4975fd1-60bc-4185-8494-14bb651525a6")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
