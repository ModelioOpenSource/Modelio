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
 * Proxy class to handle a {@link Dependency} with << UML2Signal >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("429f7033-665a-4704-999e-c1f22086cd88")
public class UML2Signal {
    @objid ("c81a90c0-1268-45f6-a297-a50e2d106724")
    public static final String STEREOTYPE_NAME = "UML2Signal";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("538383c1-a336-4630-9287-685166bc5b5f")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2Signal proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2Signal >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("540c9904-ceb6-41dc-a478-03c473e7c80a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2Signal >> then instantiate a {@link UML2Signal} proxy.
     * 
     * @return a {@link UML2Signal} proxy on the created {@link Dependency}.
     */
    @objid ("8fa7d033-68a1-4122-8b02-261a360c4748")
    public static UML2Signal create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Signal.STEREOTYPE_NAME);
        return UML2Signal.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2Signal} proxy or <i>null</i>.
     */
    @objid ("ec660e94-6677-4233-986a-d1363bc4abf9")
    public static UML2Signal instantiate(Dependency obj) {
        return UML2Signal.canInstantiate(obj) ? new UML2Signal(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Signal} proxy from a {@link Dependency} stereotyped << UML2Signal >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2Signal} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c37009e3-87f8-4347-977e-335cb53e412a")
    public static UML2Signal safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2Signal.canInstantiate(obj))
        	return new UML2Signal(obj);
        else
        	throw new IllegalArgumentException("UML2Signal: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("327375ba-6247-4972-b4b2-a1ad186c9788")
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
        UML2Signal other = (UML2Signal) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("6f80a17f-061f-4a1a-96b9-2324e7d1aa30")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("62639176-b7f4-4347-ae47-60835406e471")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("a329124c-cd0a-4d3c-97fd-856c50b84a53")
    protected UML2Signal(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1ae6c789-5ffa-46c0-b0ad-50c587da8536")
    public static final class MdaTypes {
        @objid ("d1b69a53-7f1d-433b-b9a4-8446399d82d8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("33e19d76-7cd0-419b-a4be-d7536f339940")
        private static Stereotype MDAASSOCDEP;

        @objid ("9af01eae-2ae1-4232-b8b2-b39d9ca6ec72")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7498c87c-1856-47d6-856a-d471bc4b50c1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "33ea7558-fb93-11df-8b5e-0027103f347c");
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
