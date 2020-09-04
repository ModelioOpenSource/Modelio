/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << mail >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3a3ea94-499f-4c05-bb6a-3566db79f132")
public class Mail {
    @objid ("29746327-0617-40d2-880a-66e252f3dcc1")
    public static final String STEREOTYPE_NAME = "mail";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("6bd38830-3956-4987-83e3-ff2928419bb7")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Mail proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << mail >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("829126ad-ce3b-4a7a-857f-b8ba12e6bccb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << mail >> then instantiate a {@link Mail} proxy.
     * 
     * @return a {@link Mail} proxy on the created {@link Artifact}.
     */
    @objid ("b7bd72ca-6c84-4b7a-b2dc-7dfd6f5ad4d7")
    public static Mail create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Mail.STEREOTYPE_NAME);
        return Mail.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Mail} proxy or <i>null</i>.
     */
    @objid ("60239b05-820b-4e29-a39b-5527b314486e")
    public static Mail instantiate(Artifact obj) {
        return Mail.canInstantiate(obj) ? new Mail(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Mail} proxy from a {@link Artifact} stereotyped << mail >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Mail} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f226725f-d186-4cf5-ba73-4ed5351e9890")
    public static Mail safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Mail.canInstantiate(obj))
        	return new Mail(obj);
        else
        	throw new IllegalArgumentException("Mail: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f0669f02-68db-40df-badd-46af3069ddb9")
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
        Mail other = (Mail) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("e882e4f5-3099-4c05-a55d-4cc4fdc85dc8")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("705499b7-4e1a-436f-ae88-7f1e6372b7cd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1cfd3898-2697-4bcd-9bd0-a0ebf3cc1935")
    protected Mail(Artifact elt) {
        this.elt = elt;
    }

    @objid ("d81ebae2-b027-4736-b68a-a9c31925809d")
    public static final class MdaTypes {
        @objid ("f00af2c1-4711-482e-b7dc-e1016f58734f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("426c7c2c-18e0-44d9-b59f-3c7e25279e3f")
        private static Stereotype MDAASSOCDEP;

        @objid ("8110877a-10f7-4680-951d-82707d680fa0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("10dd2820-0674-4b52-8463-5215ce45e722")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "74d7cf69-58eb-48e4-b71a-e5bb0f7570f7");
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
