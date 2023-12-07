/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.model.property.stereotype.creator;

import java.util.List;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.model.ui.swt.selectmetaclass.IMetaclassSelectorListener;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * This class checks the unicity of a stereotype name and the existence of a metaclass.
 * <p>
 * When a name is not unique in its namespace, the appropriate text field is set to red instead of green.<br>
 * When a metaclass name is invalid, the corresponding combo is also set to red instead of green.<br>
 * An invalid element means the dialog ok button is disabled, to avoid creating this stereotype as is.
 */
@objid ("8ce3d61a-38e2-4359-872b-e2a1a3f85a2d")
public class StereotypeValidator implements ModifyListener, IMetaclassSelectorListener {
    @objid ("e9a7ea2a-cf43-4c6c-a594-6f66e3d6a56a")
    private StereotypeEditionDialog dialog = null;

    @objid ("f7996cd3-67af-418f-8243-f1b69be80bf6")
    private StereotypeEditionDataModel dataModel = null;

    @objid ("66f5a01f-4a7c-4dc1-8598-022e430e1d85")
    private static final Pattern StereotypeNamePattern = Pattern.compile("[\\p{L}\\p{N}\\._ ]+");

    @objid ("2388227e-0f89-4735-99d7-c178d5b8b7dd")
    private IMModelServices mmServices;

    /**
     * Instantiates a new stereotype validator for this dialog and data model.
     * @param dialog the dialog containing the text to color red or green, and the button to enable/disable.
     * @param dataModel the data model of the dialog, to save the infos into.
     * @param mmServices model services
     */
    @objid ("23095ec1-2b3a-48e7-9f1a-1cdb829389d0")
    public  StereotypeValidator(StereotypeEditionDialog dialog, StereotypeEditionDataModel dataModel, IMModelServices mmServices) {
        this.dialog = dialog;
        this.dataModel = dataModel;
        this.mmServices = mmServices;
        
    }

    @objid ("974c6017-3b97-4302-ba48-442baaf1c1ba")
    @Override
    public void selectMetaclass(MClass mClass) {
        validate(mClass);
    }

    @objid ("920a7409-b0d4-491d-9d03-50f83d63c3e0")
    @Override
    public void modifyText(ModifyEvent e) {
        MClass stereotypeMClass = this.dialog.getBaseClass();
        validate(stereotypeMClass);
        
    }

    @objid ("c8de7fa7-bdc9-4153-afca-b997c2da78c0")
    private void validate(MClass stereotypeMClass) {
        String stereotypeName = this.dialog.stereotypeNameText.getText();
        if (stereotypeMClass != null) {
            if (stereotypeName != null
                    && !stereotypeName.isEmpty()
                    && validateStereotypeName(stereotypeName)
                    && !stereotypeNameExists(stereotypeMClass, stereotypeName)) {
                this.dialog.invalidateStereotypeNameText(false);
                this.dialog.createButton.setEnabled(true);
            } else {
                this.dialog.invalidateStereotypeNameText(true);
                this.dialog.createButton.setEnabled(false);
            }
            this.dataModel.setMetaclassName(stereotypeMClass.getQualifiedName());
        } else  {
                this.dialog.createButton.setEnabled(false);
        }
        
        this.dataModel.setStereotypeName(stereotypeName);
        
    }

    /**
     * @return
     */
    @objid ("6ca03d00-d66a-4ba6-950e-47dc079b5c0c")
    private boolean stereotypeNameExists(MClass stereotypeMetaclass, String stereotypeName) {
        List<Stereotype> stereotypes = this.mmServices.findStereotypes(".*",stereotypeName, stereotypeMetaclass);
        
        stereotypes.remove(this.dataModel.getEditedStereotype());
        return ! stereotypes.isEmpty() ;
    }

    @objid ("ec402d9f-097c-4751-831b-359eefb677d8")
    protected boolean validateStereotypeName(String name) {
        return StereotypeNamePattern.matcher(name).matches();
    }

}
