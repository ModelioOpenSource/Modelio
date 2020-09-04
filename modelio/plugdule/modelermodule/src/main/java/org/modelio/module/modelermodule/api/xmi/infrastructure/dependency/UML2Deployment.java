/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
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
 * Proxy class to handle a {@link Dependency} with << UML2Deployment >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fc3acabf-4f25-4ad1-b9d8-51446901052b")
public class UML2Deployment {
    @objid ("dc9f9cce-8b9b-4dd4-9cf3-91c953ce7fed")
    public static final String STEREOTYPE_NAME = "UML2Deployment";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5a2e9beb-6ee9-4ca4-aa2e-7a2697694ba1")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Deployment proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Deployment >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("805bc78a-f9e2-4649-a563-619d6b8668ec")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Deployment >> then instantiate a {@link UML2Deployment} proxy.
     * 
     * @return a {@link UML2Deployment} proxy on the created {@link Dependency}.
     */
    @objid ("e25eb3d2-cc59-4cee-912a-0fc74a9f6e3f")
    public static UML2Deployment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Deployment.STEREOTYPE_NAME);
        return UML2Deployment.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Deployment} proxy or <i>null</i>.
     */
    @objid ("204a8646-440b-4d01-bfb7-2d96e10107e9")
    public static UML2Deployment instantiate(Dependency obj) {
        return UML2Deployment.canInstantiate(obj) ? new UML2Deployment(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Deployment} proxy from a {@link Dependency} stereotyped << UML2Deployment >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Deployment} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8f69a2a0-2047-43a6-b151-9577e1d103be")
    public static UML2Deployment safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Deployment.canInstantiate(obj))
        	return new UML2Deployment(obj);
        else
        	throw new IllegalArgumentException("UML2Deployment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("24107d02-d00b-4a1b-8197-512f033d1734")
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
        UML2Deployment other = (UML2Deployment) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("9e6f0b19-391a-4eca-9c3a-0e4eed4ab524")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("12882fb4-1143-401d-b96b-8c582fd3bcac")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d84cadce-fc88-4230-a689-1836524d55a1")
    protected UML2Deployment(Dependency elt) {
        this.elt = elt;
    }

    @objid ("3c7b3b03-b7b6-4b5e-b616-ef29bb173f1b")
    public static final class MdaTypes {
        @objid ("b32a8e34-5b4a-43b6-9948-6e534c92fb58")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("10bc93b6-b1b0-4d68-b026-e3d353aaa394")
        private static Stereotype MDAASSOCDEP;

        @objid ("79678a61-5c1e-438e-94b0-fcbf9ef3c981")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9020070d-12fa-4ef0-b257-381e7270acc7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6faa55e3-5d0b-11df-a996-001302895b2b");
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
