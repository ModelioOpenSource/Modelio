/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.command.standard;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic model element creation command handler.
 * <p>
 * This handler supports the following parameters;
 * <ul>
 * <li>name : the handler identifier
 * <li>metaclass : the metaclass name
 * <li>stereotype : the stereotype name. May be a regular expression. May be "module#stereotype" where module and stereotype are plain text or regular expression. "#" must not be used in the module or stereotype name.
 * <li>relation : the meta relation name from the owner to the new element.
 * </ul>
 */
@objid ("1b94e3af-0e03-11e2-baba-001ec947c8cc")
public class ElementCreationStandardHandler extends DefaultModuleCommandHandler {
    @objid ("9ffe4457-7740-4f79-90e4-78bc901cbbb4")
    private String name;

    @objid ("9f2d16fb-11a8-42c6-8a30-180a287d824d")
    private String metaclass;

    @objid ("0848bfbe-2ca0-4aac-b2df-c62605fea89d")
    private String stereotype;

    @objid ("1b21770c-ed8a-4642-b2d8-cf9dfb3c51d4")
    private String relation;

    /**
     * Command parameter containing the name of the element to create.
     */
    @objid ("af95f11d-7a04-4df6-8352-d0b6ffad1b76")
    public static final String CREATION_NAME = "name";

    /**
     * Command parameter containing the metaclass of the element to create.
     */
    @objid ("fabbf65a-c07c-4534-ae0c-709625b048b8")
    public static final String METACLASS_NAME = "metaclass";

    /**
     * Command parameter created element's stereotype. Might be <code>null</code>.
     */
    @objid ("5f9042f3-50c5-4ca4-b848-d074ce97fa5d")
    public static final String STEREOTYPE_NAME = "stereotype";

    /**
     * Command parameter containing the created element's relation towards its parent. <code>null</code> means the default composition relation is used.
     */
    @objid ("e0391117-ba38-43f0-9a83-ed7e7f7bcfa6")
    public static final String RELATION_NAME = "relation";

    /**
     * Default constructor.
     */
    @objid ("a93e0001-2d2a-49e7-b274-37e1add6fdb7")
    public ElementCreationStandardHandler() {
    }

    @objid ("eb1d0f60-bd1f-479b-ad33-d44900eb029e")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModuleContext moduleContext = module.getModuleContext();
        IModelingSession modelingSession = moduleContext.getModelingSession();
        String handlerLabel = moduleContext.getI18nSupport().getString(getName());
        
        try (ITransaction tr = modelingSession.createTransaction("Create <<" + getStereotype() + ">> " + getMetaclass())) {
        
            // Create new instance of the element
            IUmlModel modelFactory = modelingSession.getModel();
            MObject newElement = modelFactory.createElement(getMetaclass());
        
            // Put new instance "under" its parent.
            ModelElement parent = (ModelElement) selectedElements.get(0);
            if (newElement instanceof AbstractDiagram) {
                ((AbstractDiagram) newElement).setOrigin(parent);
                // Apply stereotype (if any).
                if (getStereotype() != null) {
                    Stereotype ster = findStereotypeFromSpec(module, newElement.getMClass(), getStereotype());
                    if (ster != null) {
                        ((ModelElement) newElement).getExtension().add(ster);
                    }
                }
        
                modelFactory.getDefaultNameService().setDefaultName((ModelElement) newElement, handlerLabel);
        
                postConfigureElement(newElement, module);
            } else {
                // Get dependency by name.
                MDependency dependency = parent.getMClass().getDependency(getRelation());
                if (dependency == null) {
        
                    dependency = parent.getMClass().getMetamodel().getMExpert().getDefaultCompositionDep(parent, newElement);
                }
                if (dependency != null) {
                    // Append new instance of said dependency
                    parent.mGet(dependency).add(newElement);
        
                    if (getStereotype() != null) {
                        Stereotype ster = null;
                        if (getStereotype().contains("#")) {
                            String moduleName = getStereotype().substring(0, getStereotype().indexOf("#"));
                            String stereotypeName = getStereotype().substring(getStereotype().indexOf("#") + 1, getStereotype().length());
                            ster = modelingSession.getMetamodelExtensions().getStereotype(moduleName, stereotypeName, newElement.getMClass());
                        } else {
                            ster = modelingSession.getMetamodelExtensions().getStereotype(getStereotype(), newElement.getMClass());
                        }
        
                        if (ster != null) {
                            ((ModelElement) newElement).getExtension().add(ster);
                        }
                    }
        
                    modelFactory.getDefaultNameService().setDefaultName((ModelElement) newElement, handlerLabel);
        
                    MTools.get(newElement).getConfigurator().configure(newElement, new HashMap<String, Object>());
        
                    postConfigureElement(newElement, module);
                } else {
                    newElement.delete();
                }
            }
        
            if (newElement != null && newElement.isValid()) {
                tr.commit();
        
                moduleContext.getModelioServices().getNavigationService().fireNavigate(newElement);
            } else {
                tr.rollback();
            }
        }
    }

    @objid ("4161d8db-de05-4aa4-88de-ff5ce1cbbdf7")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        return selectedElements.size() > 0;
    }

    /**
     * Hook called once the element is created and configured and before the transaction is committed.
     * <p>
     * does nothing by default. Sub classes may redefine this method to make additional modifications.
     * </p>
     * <p>
     * Deleting "newElement" in this method triggers an automatic rollback for the current transaction.
     * </p>
     * 
     * @param newElement the new created element
     * @param module the module
     */
    @objid ("74bd3a00-9f9d-4f25-b134-daa0d4250e40")
    protected void postConfigureElement(MObject newElement, IModule module) {
        // Nothing to do by default.
    }

    @objid ("48954d6e-a832-45a1-9d17-ce45f0cff3d4")
    @Override
    public void initialize(List<ElementScope> scopes, Map<String, String> hParameters) {
        super.initialize(scopes, hParameters);
        
        for (Entry<String, String> param : hParameters.entrySet()) {
            switch (param.getKey()) {
            case METACLASS_NAME:
                this.metaclass = param.getValue();
                break;
            case STEREOTYPE_NAME:
                this.stereotype = param.getValue();
                break;
            case RELATION_NAME:
                this.relation = param.getValue() == null ? "" : param.getValue();
                break;
            case CREATION_NAME:
                this.name = param.getValue();
                break;
            default:
                continue;
            }
        }
    }

    /**
     * @return the created element's name.
     */
    @objid ("95c9d958-64b4-4687-b056-1fe690483b7d")
    protected String getName() {
        return this.name;
    }

    /**
     * @return the created element's metaclass.
     */
    @objid ("4e07f8c9-875e-4603-9f71-4e12f82c01b1")
    protected String getMetaclass() {
        return this.metaclass;
    }

    /**
     * @return the created element's relation towards its parent. <code>null</code> means the default composition relation is used.
     */
    @objid ("9d2bbf2a-dd60-4791-bd4c-bca072791338")
    protected String getRelation() {
        return this.relation;
    }

    /**
     * @return the created element's stereotype. Might be <code>null</code>.
     */
    @objid ("1c7f7c5c-15aa-4e73-bd50-34ed18acc455")
    protected String getStereotype() {
        return this.stereotype;
    }

}
