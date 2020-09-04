/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2TemplateSignature >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c64395f8-85c2-42fe-9a80-f23505af725e")
public class UML2TemplateSignature {
    @objid ("61d7817c-7044-4e4a-a95d-a3f3f4df30b0")
    public static final String STEREOTYPE_NAME = "UML2TemplateSignature";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("3566d029-f697-477e-af86-fb5c68d84790")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2TemplateSignature proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2TemplateSignature >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c17f5c2b-9d65-432b-b2a4-55b59fc98a71")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2TemplateSignature >> then instantiate a {@link UML2TemplateSignature} proxy.
     * 
     * @return a {@link UML2TemplateSignature} proxy on the created {@link Operation}.
     */
    @objid ("41bc7db3-5e72-4cc5-83fe-13b1a2b5070e")
    public static UML2TemplateSignature create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2TemplateSignature.STEREOTYPE_NAME);
        return UML2TemplateSignature.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2TemplateSignature} proxy or <i>null</i>.
     */
    @objid ("1caf26eb-929e-4c44-8f8e-74d0740dfef6")
    public static UML2TemplateSignature instantiate(Operation obj) {
        return UML2TemplateSignature.canInstantiate(obj) ? new UML2TemplateSignature(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2TemplateSignature} proxy from a {@link Operation} stereotyped << UML2TemplateSignature >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2TemplateSignature} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("574a33af-30c4-4736-a759-c7311070ab03")
    public static UML2TemplateSignature safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2TemplateSignature.canInstantiate(obj))
        	return new UML2TemplateSignature(obj);
        else
        	throw new IllegalArgumentException("UML2TemplateSignature: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1d60c443-4174-4740-8eee-37269f3c85ad")
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
        UML2TemplateSignature other = (UML2TemplateSignature) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("8e3cd9a6-d1b3-4474-857b-839ebd13fba1")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("4c8e7266-2d1d-4c6b-9674-33d460545456")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("3f968ae0-fc0f-4908-b24a-89b5cbeccef1")
    protected UML2TemplateSignature(Operation elt) {
        this.elt = elt;
    }

    @objid ("bd5513f3-a00c-4eb5-a954-b968c48149ed")
    public static final class MdaTypes {
        @objid ("c2e2272a-82c0-44ed-9c74-340a77705990")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0f768e17-788c-4f3c-b983-dd49f6e4eb7d")
        private static Stereotype MDAASSOCDEP;

        @objid ("3184b309-70a0-48f7-9c81-2d3a69498908")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9e91e31b-b0e6-4af6-8be4-817142679e9d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "554cb8bb-5d0e-11df-a996-001302895b2b");
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
