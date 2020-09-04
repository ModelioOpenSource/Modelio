/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.mda.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << MDAAssocDep >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Used to mark Dependencies representing an emulated association in a MDA profiled model.</i></p>
 */
@objid ("ff4c64b9-ac0c-4cbb-b591-dd6886604c51")
public class MDAAssocDep {
    @objid ("8d4b30a0-d81a-4147-8f0c-ab9655876375")
    public static final String STEREOTYPE_NAME = "MDAAssocDep";

    @objid ("77a9d8a1-e8f9-4312-a5be-02005472896e")
    public static final String ROLE_TAGTYPE = "role";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("fb2d5a62-27dd-45f3-8da3-94e30f587c7b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MDAAssocDep proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << MDAAssocDep >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fd136b25-1d11-4839-8abd-b52a275d5897")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << MDAAssocDep >> then instantiate a {@link MDAAssocDep} proxy.
     * 
     * @return a {@link MDAAssocDep} proxy on the created {@link Dependency}.
     */
    @objid ("925f2452-b485-4fee-a524-40dab20cae14")
    public static MDAAssocDep create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME);
        return MDAAssocDep.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MDAAssocDep} proxy or <i>null</i>.
     */
    @objid ("efd4db9b-064d-40d2-b026-0687d00debee")
    public static MDAAssocDep instantiate(Dependency obj) {
        return MDAAssocDep.canInstantiate(obj) ? new MDAAssocDep(obj) : null;
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link MDAAssocDep} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c4f4cff9-8d7f-44a4-ba17-334fb0dceaf9")
    public static MDAAssocDep safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MDAAssocDep.canInstantiate(obj))
        	return new MDAAssocDep(obj);
        else
        	throw new IllegalArgumentException("MDAAssocDep: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ee400c52-7798-4992-9168-4486fae8d95b")
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
        MDAAssocDep other = (MDAAssocDep) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("7e1b8467-da83-4bab-9b4f-0c2871e85f8f")
    public Dependency getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("21ba5df6-09e5-46b4-a13b-fafe5fbfc19f")
    public String getRole() {
        return this.elt.getTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT);
    }

    @objid ("0635216c-53fc-4bcb-b61e-47a342592627")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("969c78e3-a3a1-4999-8443-e8034be258a0")
    public void setRole(String value) {
        this.elt.putTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT, value);
    }

    @objid ("e109c8a2-138c-401f-b7c7-5cbd4db0f71b")
    protected MDAAssocDep(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d7dd74b-8894-4e3b-9fbc-53ed8d225760")
    public static final class MdaTypes {
        @objid ("ed02ffb9-1be5-4c2a-952d-d26029087566")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6b2b4bf7-8052-4522-81d8-54a065ba8e30")
        public static TagType ROLE_TAGTYPE_ELT;

        @objid ("d34ef06f-ff1b-4d5a-bd48-5f805e45d431")
        private static Stereotype MDAASSOCDEP;

        @objid ("ca11897f-7083-44b5-97ae-c92d432c13d5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7b4bda98-c023-4890-aa1f-84d00903d192")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            ROLE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
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
