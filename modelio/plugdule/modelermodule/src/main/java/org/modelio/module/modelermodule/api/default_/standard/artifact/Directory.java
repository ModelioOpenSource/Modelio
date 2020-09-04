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
 * Proxy class to handle a {@link Artifact} with << directory >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d378822b-d3d3-4f51-9c86-6bd5d820f76d")
public class Directory {
    @objid ("5190d628-d359-4aef-95c1-ecfe4db0a28e")
    public static final String STEREOTYPE_NAME = "directory";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("438c5378-7679-4d7f-9924-2112144d524c")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Directory proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << directory >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("297b63d6-22bf-4528-90f0-ca388804d9fc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << directory >> then instantiate a {@link Directory} proxy.
     * 
     * @return a {@link Directory} proxy on the created {@link Artifact}.
     */
    @objid ("4b09ff05-d573-4bc4-8e58-9740865d59c6")
    public static Directory create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Directory.STEREOTYPE_NAME);
        return Directory.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Directory} proxy or <i>null</i>.
     */
    @objid ("f5df413f-7a09-4ab5-b139-4f6ef6fe19e4")
    public static Directory instantiate(Artifact obj) {
        return Directory.canInstantiate(obj) ? new Directory(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Directory} proxy from a {@link Artifact} stereotyped << directory >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Directory} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("65530ee6-4a8a-49cc-b05c-6a6ef483102a")
    public static Directory safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Directory.canInstantiate(obj))
        	return new Directory(obj);
        else
        	throw new IllegalArgumentException("Directory: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("801fa1de-4cf1-4a91-8be4-335f7a680fba")
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
        Directory other = (Directory) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("30d61577-8073-4023-be65-3a200f4353ba")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("f41eb784-30bd-4909-be82-77faf619450f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("be04ee37-b115-48b8-bf4c-b14a73c75e4a")
    protected Directory(Artifact elt) {
        this.elt = elt;
    }

    @objid ("c8a5a0d9-e215-4019-9561-d0323c58164f")
    public static final class MdaTypes {
        @objid ("2a4338cb-b169-4e4f-be96-cb54758bc864")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("392897e2-1aea-4a3c-85bd-5df9aa69173b")
        private static Stereotype MDAASSOCDEP;

        @objid ("a2d312a6-99bb-466f-a245-0a8bec1419c4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("55d7be64-48f8-4509-9093-dfbde0db520f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6432b987-256f-4121-9428-a89d364c2cef");
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
