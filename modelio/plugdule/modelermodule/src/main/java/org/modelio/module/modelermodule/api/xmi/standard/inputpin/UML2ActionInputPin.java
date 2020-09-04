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
 * Proxy class to handle a {@link InputPin} with << UML2ActionInputPin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3bedd0c8-c338-4af8-95d9-6c58807f3ac3")
public class UML2ActionInputPin {
    @objid ("a11db61e-0111-4405-ba68-35b0ff96a1a5")
    public static final String STEREOTYPE_NAME = "UML2ActionInputPin";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("c2c0f272-e9d6-41b8-a83e-0fbe0d19ac0a")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2ActionInputPin proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2ActionInputPin >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8ab53fe9-1811-47b5-a46c-e88a1edff303")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2ActionInputPin >> then instantiate a {@link UML2ActionInputPin} proxy.
     * 
     * @return a {@link UML2ActionInputPin} proxy on the created {@link InputPin}.
     */
    @objid ("293a5e10-f3b2-4d4b-b1cd-606ebd934b65")
    public static UML2ActionInputPin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ActionInputPin.STEREOTYPE_NAME);
        return UML2ActionInputPin.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2ActionInputPin} proxy or <i>null</i>.
     */
    @objid ("75b95d76-d521-4f2e-bdc7-afe522335dd6")
    public static UML2ActionInputPin instantiate(InputPin obj) {
        return UML2ActionInputPin.canInstantiate(obj) ? new UML2ActionInputPin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ActionInputPin} proxy from a {@link InputPin} stereotyped << UML2ActionInputPin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2ActionInputPin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ebac353a-ac66-467c-9564-f9ac7ae1e75a")
    public static UML2ActionInputPin safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2ActionInputPin.canInstantiate(obj))
        	return new UML2ActionInputPin(obj);
        else
        	throw new IllegalArgumentException("UML2ActionInputPin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8923a6c2-3d4c-419c-9b4e-b76c0aaf1c72")
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
        UML2ActionInputPin other = (UML2ActionInputPin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("007079d6-07df-4631-a982-ca8117eedb80")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("a25476c8-579c-418a-a210-19795eea7da8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7b850e4c-a195-46fa-9be9-48bd268a2727")
    protected UML2ActionInputPin(InputPin elt) {
        this.elt = elt;
    }

    @objid ("dc5d92d9-0b36-408d-b559-7f7dd32d402b")
    public static final class MdaTypes {
        @objid ("7bb4a7af-4f7b-4428-9d9e-8ebfa0f3c1f4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d389801b-4f6c-475f-a2e0-8dc685373936")
        private static Stereotype MDAASSOCDEP;

        @objid ("d6be0ced-4355-4e64-9dcd-b3c74965486d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6c98a182-b310-4eb2-8e91-753a7a86fffb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0bd72298-5d08-11df-a996-001302895b2b");
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
