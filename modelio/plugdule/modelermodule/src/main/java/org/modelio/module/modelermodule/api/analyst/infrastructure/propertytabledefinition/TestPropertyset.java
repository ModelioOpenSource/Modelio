/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

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
 * Proxy class to handle a {@link PropertyTableDefinition} with << test_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6d0ae6b5-5de2-44ca-873b-f025f26616a4")
public class TestPropertyset {
    @objid ("720c27e1-668e-465b-b583-61996b2bbd1a")
    public static final String STEREOTYPE_NAME = "test_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("085c3529-d9f3-41e4-894a-62da2a9139e4")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link TestPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << test_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e89f20f4-fa9e-4bbd-87da-d81a49554218")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << test_propertyset >> then instantiate a {@link TestPropertyset} proxy.
     * 
     * @return a {@link TestPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("8868bed4-d59f-4e63-84e9-32c3d12ab252")
    public static TestPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TestPropertyset.STEREOTYPE_NAME);
        return TestPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link TestPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << test_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link TestPropertyset} proxy or <i>null</i>.
     */
    @objid ("86e225d1-231a-47fe-be5c-5fa148a59b50")
    public static TestPropertyset instantiate(PropertyTableDefinition obj) {
        return TestPropertyset.canInstantiate(obj) ? new TestPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TestPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << test_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link TestPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0b8860a4-10d4-478d-ac4f-96f3a1ef0f75")
    public static TestPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (TestPropertyset.canInstantiate(obj))
        	return new TestPropertyset(obj);
        else
        	throw new IllegalArgumentException("TestPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a7b4c372-d698-4eb8-a42b-c1bfd122c922")
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
        TestPropertyset other = (TestPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("0a5c98ea-b6b1-4c7f-8042-9c1c1cfa8128")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("a5bc43cd-e553-4ae1-ac64-5c5b2a20db31")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5b2c936e-3493-411e-8220-278787310f28")
    protected TestPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("ba30c140-5731-4627-af12-930f1598286d")
    public static final class MdaTypes {
        @objid ("06292c12-89c2-453f-8cc8-f2c370df3115")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fb5e34c9-244f-43d9-9f76-091c615701eb")
        private static Stereotype MDAASSOCDEP;

        @objid ("989cf863-2727-4fb8-8250-cf1017e78d4e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7e373f0a-9245-436b-b314-b979ae0161bc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "859f8b76-5acc-4a9c-a5eb-973467388b13");
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
