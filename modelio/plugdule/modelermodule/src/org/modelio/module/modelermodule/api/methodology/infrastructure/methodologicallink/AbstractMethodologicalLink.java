/* 
 * Copyright 2013-2020 Modeliosoft
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
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link MethodologicalLink} with << AbstractMethodologicalLink >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d195a31b-2d14-4c22-8f7f-27b89a4104d1")
public abstract class AbstractMethodologicalLink {
    @objid ("ca36b2aa-06c8-44fa-9e66-f6ad04045973")
    public static final String STEREOTYPE_NAME = "AbstractMethodologicalLink";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("4c5a0346-2ec7-4cef-a4cc-4acc548cdad0")
    protected final MethodologicalLink elt;

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("b363c12d-c3db-4447-b55b-4eac5f014866")
    public static ModelElement getTarget(ModelElement source, Stereotype ste) {
        return source.getDependsOnDependency(MethodologicalLink.class).stream()
                .filter(dep -> ste != null && dep.isStereotyped(ste))
                .map(dep -> dep.getDependsOn())
                .filter(elt -> elt != null)
                .findFirst()
                .orElse(null);
        
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("a3549f13-e461-42c4-8a00-3fe6e693a60d")
    public static Collection<ModelElement> getTargets(ModelElement source, Stereotype ste) {
        return source.getDependsOnDependency(MethodologicalLink.class).stream()
                .filter(dep -> ste != null && dep.isStereotyped(ste))
                .map(dep -> dep.getDependsOn())
                .filter(elt -> elt != null)
                .collect(Collectors.toList());
        
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("38fbc225-054b-4cdd-b435-8acaa0764e7c")
    public static void setTarget(ModelElement source, Stereotype ste, ModelElement target) {
        boolean found = false;
        for (MethodologicalLink dep : new ArrayList<>(source.getDependsOnDependency(MethodologicalLink.class))) {
            if (dep.isStereotyped(ste)) {
                if (!found && target != null) {
                    dep.setDependsOn(target);
                    found = true;
                } else {
                    dep.delete();
                }
            }
        }
        
        if (!found && target != null) {
            MethodologicalLink newLink = (MethodologicalLink) ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement(MethodologicalLink.MQNAME);
            newLink.getExtension().add(ste);
            newLink.setImpacted(source);
            newLink.setDependsOn(target);
        }
        
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("7401357b-8d1b-4525-9010-496766911b3b")
    public static void setTargets(ModelElement source, Stereotype ste, Collection<ModelElement> targets) {
        // remove obsolete deps
        List<Dependency> depsAccess = source.getDependsOnDependency();
        
        List<Dependency> oldDeps = new ArrayList<>(depsAccess)
                .stream()
                .filter(d -> d instanceof MethodologicalLink && d.getExtension().contains(ste))
                .collect(Collectors.toList());
        
        for (Dependency d : oldDeps) {
            if (!targets.contains(d.getDependsOn())) {
                d.delete();
            }
        }
        
        // Add new ones
        IUmlModel modelFactory = ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel();
        for (ModelElement target : targets) {
            if (oldDeps.stream().noneMatch(d -> d.getDependsOn().equals(target))) {
                MethodologicalLink newLink = (MethodologicalLink) modelFactory.createElement(MethodologicalLink.MQNAME);
                newLink.setDependsOn(target);
                newLink.setImpacted(source);
                newLink.getExtension().add(ste);
            }
        }
        
    }

    @objid ("32cde6b4-0a32-41eb-ab00-457cf2e4cb44")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractMethodologicalLink other = (AbstractMethodologicalLink) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("7503c73b-f345-43b7-9aea-7cda02a36f64")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("fb7dcc5f-0a1b-4ae5-ae3d-e845db1d5599")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("f4b63b89-a8a4-4195-ae14-3a15f3b07688")
    protected  AbstractMethodologicalLink(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("499bde89-6532-4207-b69a-ee50e9910be1")
    public static final class MdaTypes {
        @objid ("b6715546-d83b-40fd-9b24-4a402677440a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d8c2b9ce-5ab8-41ae-bb51-15256f4a6714")
        private static Stereotype MDAASSOCDEP;

        @objid ("31112904-002b-4c8d-9dbf-201a2db646ac")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9aa97b40-3c5f-45ba-9f92-a4a968005a1b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "216c1c7f-0ffc-453c-9559-41aeff7e3510");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }

	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}
