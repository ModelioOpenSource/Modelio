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
 * Proxy class to handle a {@link Artifact} with << library >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6adfb96d-3843-4138-b8fa-f7724e9f9ec8")
public class Library {
    @objid ("898f67de-5805-47a6-9011-23506319634c")
    public static final String STEREOTYPE_NAME = "library";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("41317e24-e33e-4ed6-884e-6dea67e69337")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Library proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << library >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("895390a6-4581-400b-ad73-c2e8b5b138cd")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << library >> then instantiate a {@link Library} proxy.
     * 
     * @return a {@link Library} proxy on the created {@link Artifact}.
     */
    @objid ("faf17410-dc8b-45c2-a4c4-aef687220c01")
    public static Library create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Library.STEREOTYPE_NAME);
        return Library.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Library} proxy or <i>null</i>.
     */
    @objid ("b0a1eb19-0d08-417e-9b75-3b60776396a6")
    public static Library instantiate(Artifact obj) {
        return Library.canInstantiate(obj) ? new Library(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Library} proxy from a {@link Artifact} stereotyped << library >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Library} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("aa90af85-4eb4-41bf-b267-b61693776a80")
    public static Library safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Library.canInstantiate(obj))
        	return new Library(obj);
        else
        	throw new IllegalArgumentException("Library: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f14825b4-1f59-42e5-b20e-cd17083d8b20")
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
        Library other = (Library) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("6e55acd9-a596-4c9a-b341-d2ef5d213265")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("d53cfdfa-4102-4588-8221-63eea759831c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("88eade7a-bf98-4fa4-b0cd-2bae7230a7e6")
    protected Library(Artifact elt) {
        this.elt = elt;
    }

    @objid ("7c651d53-3107-491d-9c5d-92dc5fa1b829")
    public static final class MdaTypes {
        @objid ("0ffb77e5-bd14-4251-8bc7-b17f8fa8600c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c68c657f-bb9a-45d7-9b11-15a261c73c8a")
        private static Stereotype MDAASSOCDEP;

        @objid ("36d5daf0-d07f-441a-bdb4-1a53f412af73")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ac95ac78-9ab5-453d-8023-c1f037f75ad0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c5-0000-000000000000");
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
