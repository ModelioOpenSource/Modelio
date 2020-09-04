/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
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
 * Proxy class to handle a {@link InputPin} with << UML2Second >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b741933-97e4-4335-8b95-a8f3c5ea9155")
public class UML2Second {
    @objid ("40f8b377-32b0-45fc-a8f6-c456874f16e9")
    public static final String STEREOTYPE_NAME = "UML2Second";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("c7cb8c60-0a5c-4bdd-99f5-6bfc64b831d5")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Second proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Second >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("4c2a0346-649e-4a78-af5f-c90e5da4a626")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Second >> then instantiate a {@link UML2Second} proxy.
     * 
     * @return a {@link UML2Second} proxy on the created {@link InputPin}.
     */
    @objid ("f25f23a9-a234-4579-9d2c-c4a77e5e0642")
    public static UML2Second create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Second.STEREOTYPE_NAME);
        return UML2Second.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Second} proxy or <i>null</i>.
     */
    @objid ("cedd2ad3-1064-40be-9e86-c42db9ff63ad")
    public static UML2Second instantiate(InputPin obj) {
        return UML2Second.canInstantiate(obj) ? new UML2Second(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Second} proxy from a {@link InputPin} stereotyped << UML2Second >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Second} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("83f95930-849b-462e-a94f-3ae9214ce13f")
    public static UML2Second safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Second.canInstantiate(obj))
        	return new UML2Second(obj);
        else
        	throw new IllegalArgumentException("UML2Second: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("84620148-6769-4127-beb3-2d99782770ec")
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
        UML2Second other = (UML2Second) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("29b13430-c158-4893-b9a3-adcbaf51f40a")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("e8269bdf-f667-4081-9b39-907b72662632")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ecedd70a-08fb-4faa-98f1-a7d0bd4c237f")
    protected UML2Second(InputPin elt) {
        this.elt = elt;
    }

    @objid ("12a78492-d0ac-49ef-8c8e-e162f97b0e1f")
    public static final class MdaTypes {
        @objid ("eccb83ca-2540-4c86-a4e2-cb6f01a4424f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("36732f89-bc9a-4146-831a-79d1834b2259")
        private static Stereotype MDAASSOCDEP;

        @objid ("f494f3ca-75db-4f8c-8314-b0a18223e0d6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1f1770ca-3936-4e55-acc0-93acfa7dda16")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3f03c5f1-c308-11de-8ac8-001302895b2b");
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
