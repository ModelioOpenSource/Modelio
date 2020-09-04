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
 * Proxy class to handle a {@link Dependency} with << UML2EndDestructionDataReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("13fa7cda-1af5-453f-b5a9-6961ffafa991")
public class UML2EndDestructionDataReference {
    @objid ("4f8f57e2-0159-4bd9-8060-991d5d44eec9")
    public static final String STEREOTYPE_NAME = "UML2EndDestructionDataReference";

    @objid ("01ca7611-4c91-4568-aa8d-467c25f06a93")
    public static final String ISDESTROYDUPLICATES_TAGTYPE = "IsDestroyDuplicates";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("bd02305c-7005-465d-ad35-3316b72f1730")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndDestructionDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndDestructionDataReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("70f17e43-313c-41ca-8536-195a3fd5c681")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndDestructionDataReference >> then instantiate a {@link UML2EndDestructionDataReference} proxy.
     * 
     * @return a {@link UML2EndDestructionDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("4d06600e-c274-4b46-b0e7-a3fe81a9a597")
    public static UML2EndDestructionDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndDestructionDataReference.STEREOTYPE_NAME);
        return UML2EndDestructionDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndDestructionDataReference} proxy from a {@link Dependency} stereotyped << UML2EndDestructionDataReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndDestructionDataReference} proxy or <i>null</i>.
     */
    @objid ("beec18b6-c1a8-4fbe-bb18-df093fcec5d2")
    public static UML2EndDestructionDataReference instantiate(Dependency obj) {
        return UML2EndDestructionDataReference.canInstantiate(obj) ? new UML2EndDestructionDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndDestructionDataReference} proxy from a {@link Dependency} stereotyped << UML2EndDestructionDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndDestructionDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2e0db561-ce50-48bd-8149-573e990c1493")
    public static UML2EndDestructionDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndDestructionDataReference.canInstantiate(obj))
        	return new UML2EndDestructionDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndDestructionDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8272232d-d427-4d8a-8047-91ac146c1a41")
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
        UML2EndDestructionDataReference other = (UML2EndDestructionDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("650bffe4-528b-452a-9466-1d67169527aa")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("471afff2-5108-4766-bd23-70623b083d89")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("510ceef5-4fd2-4251-92a6-bb638e747e7c")
    public boolean isIsDestroyDuplicates() {
        return this.elt.isTagged(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'IsDestroyDuplicates'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("15906822-d2e9-4b02-be4c-8d19c73cc760")
    public void setIsDestroyDuplicates(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2EndDestructionDataReference.MdaTypes.ISDESTROYDUPLICATES_TAGTYPE_ELT);
    }

    @objid ("a3eac80a-29e0-4859-971f-83bfaf3d8b31")
    protected UML2EndDestructionDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("8989eec9-3340-47a9-97c9-74399b71b10e")
    public static final class MdaTypes {
        @objid ("99cc9583-7c70-4355-909f-235dae77f620")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("45a2ce3a-6799-48bb-9d20-3a1a45b82437")
        public static TagType ISDESTROYDUPLICATES_TAGTYPE_ELT;

        @objid ("5139876f-4f0c-487c-8f71-fe1533b14934")
        private static Stereotype MDAASSOCDEP;

        @objid ("beb548e9-4440-4898-a066-df8e8d6b5990")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("af076d8c-7dab-4f72-a0cc-e9be99571ede")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a74178fb-df2b-11de-905b-001302895b2b");
            ISDESTROYDUPLICATES_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "6f99afff-df2d-11de-905b-001302895b2b");
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
