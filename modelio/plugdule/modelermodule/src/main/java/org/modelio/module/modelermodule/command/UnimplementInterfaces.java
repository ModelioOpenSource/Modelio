package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.engine.InterfaceImplementer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Delete Operations in Classifiers from those defined in their implemented Interfaces.
 */
@objid ("f7178c8d-7ee3-4b7e-9d5d-d6cd38150d3c")
public class UnimplementInterfaces extends DefaultModuleCommandHandler {
    @objid ("df36338f-64e6-4bd8-b5da-c3c0c31d92d2")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Unimplement Interfaces in class")) {
            boolean hasDoneWork = false;
            for (MObject theElement : selectedElements) {
                Classifier theClassifier = (Classifier) theElement;
        
                boolean newResult = interfaceManager.unImplementInterfaces(theClassifier);
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
    @objid ("82dfe460-3aa8-49b3-b597-8621681541ba")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        if (selectedElements.size() <= 0) {
            return false;
        }
        // The method is only active if the class implements at least one interface
        Class current = (Class) selectedElements.get(0);
        return current.getRealized().size() > 0;
    }

    /**
     * This method indicates if a command has to be deactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to deactivated the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("809a79af-70da-4968-8b90-1d78e93af464")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
