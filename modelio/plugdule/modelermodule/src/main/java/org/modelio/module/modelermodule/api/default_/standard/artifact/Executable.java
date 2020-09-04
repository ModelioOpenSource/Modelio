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
 * Proxy class to handle a {@link Artifact} with << executable >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d4573417-3d08-45a2-b10e-0d1b9d7f1d7d")
public class Executable {
    @objid ("836fe98d-4296-4bbb-8cba-4a4ada5b0e0e")
    public static final String STEREOTYPE_NAME = "executable";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("a84563ad-5335-4fab-a46a-a5a48644dd3b")
    protected final Artifact elt;

    /**
     * Tells whether a {@link Executable proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << executable >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4503b162-eb18-464a-aed6-c707cd8fa2c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << executable >> then instantiate a {@link Executable} proxy.
     * 
     * @return a {@link Executable} proxy on the created {@link Artifact}.
     */
    @objid ("c0e2e147-e4b7-4859-844e-17a29bf40555")
    public static Executable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Executable.STEREOTYPE_NAME);
        return Executable.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link Executable} proxy or <i>null</i>.
     */
    @objid ("19b3a6eb-c616-495f-9a43-bf00bab0bb01")
    public static Executable instantiate(Artifact obj) {
        return Executable.canInstantiate(obj) ? new Executable(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Executable} proxy from a {@link Artifact} stereotyped << executable >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link Executable} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("64046a15-ad77-4293-a155-1c067f8f21d3")
    public static Executable safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (Executable.canInstantiate(obj))
        	return new Executable(obj);
        else
        	throw new IllegalArgumentException("Executable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("90647822-ef72-48eb-a609-ca66584dccdd")
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
        Executable other = (Executable) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("bed68571-515c-4e7e-b60f-96785b4865c2")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("618a4bfd-81f4-4c98-87e4-18b29fd8782e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("122d7ce8-069b-4ef4-b53f-acac35a59340")
    protected Executable(Artifact elt) {
        this.elt = elt;
    }

    @objid ("51e459a7-b6fd-484f-af65-d1a7e5948cea")
    public static final class MdaTypes {
        @objid ("4c42af37-2cbb-4e4a-b1a4-92bd013a51b3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c5b11a97-bf26-4a3f-be99-c2b15f5a26b6")
        private static Stereotype MDAASSOCDEP;

        @objid ("bcd5e1c7-df52-4436-9924-a24d182da976")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("80763506-33ef-417f-9ea9-bc2cf2789d6b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01c3-0000-000000000000");
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
