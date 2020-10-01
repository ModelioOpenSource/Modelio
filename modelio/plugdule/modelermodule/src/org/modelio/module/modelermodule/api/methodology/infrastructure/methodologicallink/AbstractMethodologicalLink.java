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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("c7400608-c393-47e0-9030-c47b1529f85b")
    public static final String STEREOTYPE_NAME = "AbstractMethodologicalLink";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("179d50c2-3dad-40f6-bcfd-5a9ce0759bc7")
    protected final MethodologicalLink elt;

    @objid ("b363c12d-c3db-4447-b55b-4eac5f014866")
    public static ModelElement getTarget(ModelElement source, Stereotype ste) {
        return source.getDependsOnDependency(MethodologicalLink.class).stream()
                .filter(dep -> ste != null && dep.isStereotyped(ste))
                .map(dep -> dep.getDependsOn())
                .filter(elt -> elt != null)
                .findFirst()
                .orElse(null);
    }

    @objid ("a3549f13-e461-42c4-8a00-3fe6e693a60d")
    public static Collection<ModelElement> getTargets(ModelElement source, Stereotype ste) {
        return source.getDependsOnDependency(MethodologicalLink.class).stream()
                .filter(dep -> ste != null && dep.isStereotyped(ste))
                .map(dep -> dep.getDependsOn())
                .filter(elt -> elt != null)
                .collect(Collectors.toList());
    }

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

    @objid ("871e1929-fef4-4e98-8e64-156e78c08b9d")
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
    @objid ("e26fd243-e23d-4b27-ac53-f0d247beca59")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("33dbf5f7-5417-4ec6-9176-e254f3385aa5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ac461658-2bc8-496a-945b-3370539de173")
    protected AbstractMethodologicalLink(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("499bde89-6532-4207-b69a-ee50e9910be1")
    public static final class MdaTypes {
        @objid ("e9b2eda2-2dc7-42b3-9afa-8ee785986edd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5722dc0f-628b-4668-b518-18a6fe66c8ad")
        private static Stereotype MDAASSOCDEP;

        @objid ("3d5e7cfb-f0f4-4232-ae2f-8c31922688b9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c3fdbc17-3b54-4020-a78f-e5dcc22ff40a")
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
