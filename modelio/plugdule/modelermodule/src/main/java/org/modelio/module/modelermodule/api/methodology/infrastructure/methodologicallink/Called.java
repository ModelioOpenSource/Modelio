/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
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
 * Proxy class to handle a {@link MethodologicalLink} with << Called >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business process ou application Process doivent faire apparaitre un activity call.</i></p>
 */
@objid ("f89804bf-ce37-4ee3-9391-4817cb8a0de9")
public class Called {
    @objid ("b8d59d10-9d70-4d4e-afa9-52ad757ac677")
    public static final String STEREOTYPE_NAME = "Called";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("f87e4189-5fa9-4660-89d4-9f264adb33c8")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Called proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Called >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("296027c6-68bd-4cce-b05a-83adc3dbf92b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Called >> then instantiate a {@link Called} proxy.
     * 
     * @return a {@link Called} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("4b04d806-4f61-4e2e-934d-42d84cbb83f4")
    public static Called create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Called.STEREOTYPE_NAME);
        return Called.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Called} proxy or <i>null</i>.
     */
    @objid ("a0f31402-08dd-4e92-81c3-3dbae70d2e06")
    public static Called instantiate(MethodologicalLink obj) {
        return Called.canInstantiate(obj) ? new Called(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Called} proxy from a {@link MethodologicalLink} stereotyped << Called >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Called} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("da7bf939-0b53-48c2-a5f0-29c79d4aad9f")
    public static Called safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Called.canInstantiate(obj))
        	return new Called(obj);
        else
        	throw new IllegalArgumentException("Called: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bff86b6d-45f8-4f89-9bbe-ccce4ed5c363")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("7de0a3b2-2dae-493d-b2e1-b4d05f76b608")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("447d529e-319a-4de0-9ea3-e92fab7829e8")
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
        Called other = (Called) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("18c0874b-8762-4e75-88d9-339aced21680")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("0b6eb32d-cd16-48ed-85d3-eb6ba072c0db")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1955cc16-ff39-4769-8c57-60c5ae26c7d8")
    protected Called(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("3a055766-68e6-4266-a9ac-43448b367a1b")
    public static final class MdaTypes {
        @objid ("72490aa8-59c1-434b-949d-6bbbe3add466")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b2681826-ded8-48c5-b353-89e7bcaf543a")
        private static Stereotype MDAASSOCDEP;

        @objid ("9e2a6d6d-2260-4532-96d2-142906de1719")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("22fbadcb-cd67-4684-bddf-571c85fa6a1e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c3862c6c-5983-4d1a-b0e2-58dd2685eda0");
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
