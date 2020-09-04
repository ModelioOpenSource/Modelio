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
 * Proxy class to handle a {@link PropertyTableDefinition} with << dictionary_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a701ba85-699e-41ed-a46b-eb90d18ebaa6")
public class DictionaryPropertyset {
    @objid ("79e218bf-fcb4-45a9-a432-cf76f6fbfb45")
    public static final String STEREOTYPE_NAME = "dictionary_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("49bc5639-71ff-44b2-8748-1d3a36cfe8ef")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link DictionaryPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("88bac64f-5160-4daf-9254-de04b6bfbe6b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> then instantiate a {@link DictionaryPropertyset} proxy.
     * 
     * @return a {@link DictionaryPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("d5bee736-f5ce-4005-893c-c2183efcc484")
    public static DictionaryPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME);
        return DictionaryPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link DictionaryPropertyset} proxy or <i>null</i>.
     */
    @objid ("01b94500-9ee4-472d-9046-01ab8d05756d")
    public static DictionaryPropertyset instantiate(PropertyTableDefinition obj) {
        return DictionaryPropertyset.canInstantiate(obj) ? new DictionaryPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link DictionaryPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("45672c2b-81fd-4fe1-b654-f8e53164479a")
    public static DictionaryPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (DictionaryPropertyset.canInstantiate(obj))
        	return new DictionaryPropertyset(obj);
        else
        	throw new IllegalArgumentException("DictionaryPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1f89ccce-267a-4654-966c-80edf146fe03")
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
        DictionaryPropertyset other = (DictionaryPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("03b6062d-2a9f-465c-98b3-2055c8600d4b")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("f509c2b2-07cc-4553-99ca-2503cfdd2986")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f7000a15-1927-46eb-a3d6-1a077c6af50a")
    protected DictionaryPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("d95109a9-38e2-4ad3-8d22-511e555e6dcc")
    public static final class MdaTypes {
        @objid ("75313b8c-155f-4e3c-bd69-aa0856513b7a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("15f27914-4d7a-4195-904b-2f51713ec01a")
        private static Stereotype MDAASSOCDEP;

        @objid ("da7f845d-c92b-4bc8-b1c8-b1be95028fdd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7f5cdd8e-8aab-4dff-b1fd-b34f6fd82fbf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12fc-0000-000000000000");
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
