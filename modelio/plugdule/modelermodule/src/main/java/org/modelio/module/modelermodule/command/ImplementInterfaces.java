package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.engine.InterfaceImplementer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create Operations in the Class from those defined in the implemented Interfaces.
 */
@objid ("ab89c19f-ea26-4c00-a5b8-7430a3378a59")
public class ImplementInterfaces extends DefaultModuleCommandHandler {
    @objid ("4416a4f0-e1b1-45e4-91e5-2a2f0ef10983")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Update class from interfaces")) {
            boolean hasDoneWork = false;
            for (MObject theElement : selectedElements) {
                Classifier theClassifier = (Classifier) theElement;
        
                boolean newResult = interfaceManager.implementInterfaces(session, theClassifier);
                hasDoneWork = hasDoneWork || newResult;
            }
        
            if (hasDoneWork) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("b064f870-ac2a-4a36-8d0b-9c90aa447666")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        for (MObject theElement : selectedElements) {
            Classifier theClassifier = (Classifier) theElement;
            if (theClassifier.getRealized().size() == 0) {
                return false;
            }
        }
        return (selectedElements.size() != 0);
    }

    /**
     * This method precizes if a command has to be desactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("b027b452-af6d-4b8d-b529-84a7d4d46c63")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
