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
 * Proxy class to handle a {@link MethodologicalLink} with << Represents >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business Objects, Data Objects, Products, Artifacts, doivent par drag & drop créer des Data Objects BPMN associés.</i></p>
 */
@objid ("f455da76-7d21-4a23-86cb-44284ad9c018")
public class Represents {
    @objid ("9462dab8-90e3-4fd2-963d-936733d813b8")
    public static final String STEREOTYPE_NAME = "Represents";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("07024771-69d8-4a81-a645-5ad59455bb87")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Represents proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Represents >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a04984cb-3f5e-4a4b-b198-31f3ed4e3111")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Represents >> then instantiate a {@link Represents} proxy.
     * 
     * @return a {@link Represents} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("ccb66f40-51f4-4817-b6bc-4333af4e3162")
    public static Represents create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME);
        return Represents.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Represents} proxy or <i>null</i>.
     */
    @objid ("1c858349-5625-475f-b7fc-8198aab83c1d")
    public static Represents instantiate(MethodologicalLink obj) {
        return Represents.canInstantiate(obj) ? new Represents(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Represents} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2261c62d-2b21-4529-bfe2-7049557fe943")
    public static Represents safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Represents.canInstantiate(obj))
        	return new Represents(obj);
        else
        	throw new IllegalArgumentException("Represents: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b68488da-cf0c-4fc1-94b3-9950d52ae9bb")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("85a28a48-571f-47ac-b1b3-7f5fb1f27c90")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("370b1ba6-4d65-41ec-a9d9-f685ef772e9a")
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
        Represents other = (Represents) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("2dfa1fa3-0a93-4243-8214-e4aa057a16ab")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("24cdad25-ac95-4da3-9438-d82b56ca77cd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d2bdf212-3c75-491e-8c23-9152a4420cca")
    protected Represents(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("d9896322-b59f-496b-99be-d10d51513d32")
    public static final class MdaTypes {
        @objid ("e265d247-f234-418d-a83e-86b461edfccb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ed0b88e3-cfed-483b-a0c4-566af0d2cf67")
        private static Stereotype MDAASSOCDEP;

        @objid ("4119664d-9328-4fe0-a374-143aecda46bb")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4d947ead-2f0f-4d9c-a00f-06fe67f943d1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f5d2927d-46d6-4d87-9cf2-adb4a47ca929");
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
