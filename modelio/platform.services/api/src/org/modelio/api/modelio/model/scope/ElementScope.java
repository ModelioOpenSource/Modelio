/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model.scope;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class encapsulates a scope definition for a command.
 * <p>
 * A scope is composed of a metaclass and a stereotype. Both values are optional but at least one of these two fields must be provided.
 * </p>
 */
@objid ("38fa7d52-b2f9-489f-b714-1150b96d4529")
public final class ElementScope {
    @mdl.prop
    @objid ("b73303fb-79ff-4798-8092-207c860b4833")
    private final boolean withSubClasses;

    @mdl.propgetter
    public boolean isWithSubClasses() {
        // Automatically generated method. Please do not modify this code.
        return this.withSubClasses;
    }

    @mdl.prop
    @objid ("6c0da0ee-66d6-4b70-8834-8bf427feab93")
    private final boolean withSubStereotypes;

    @mdl.propgetter
    public boolean isWithSubStereotypes() {
        // Automatically generated method. Please do not modify this code.
        return this.withSubStereotypes;
    }

    @mdl.prop
    @objid ("88786c3b-fe6a-4603-a892-3cf93abf11df")
    private final MClass metaclass;

    @mdl.propgetter
    public MClass getMetaclass() {
        // Automatically generated method. Please do not modify this code.
        return this.metaclass;
    }

    @mdl.prop
    @objid ("e7569083-4548-4904-bac0-501d771c1c8f")
    private final Stereotype stereotype;

    @mdl.propgetter
    public Stereotype getStereotype() {
        // Automatically generated method. Please do not modify this code.
        return this.stereotype;
    }

    /**
     * A scope is composed of a metaclass and a stereotype.
     * <p>
     * Both values are optional but at least one of these two fields must be provided.
     * </p>
     * <p>
     * Calling this constructor is equivalent to <code>new ElementScope(metaclass, true, stereotype, true);</code>
     * </p>
     * 
     * @param metaclass a metaclass. Might be <code>null</code>
     * @param stereotype a stereotype. Might be <code>null</code>
     * @deprecated use {@link #ElementScope(MClass, boolean, Stereotype, boolean)} instead.
     */
    @objid ("72afc387-e9c2-4231-9df0-918823be1ff9")
    @Deprecated
    public ElementScope(MClass metaclass, Stereotype stereotype) {
        this(metaclass, true, stereotype, true);
    }

    /**
     * A scope is composed of a metaclass and a stereotype.
     * <p>
     * Both values are optional but at least one of these two fields must be provided.
     * </p>
     * 
     * @param metaclass a metaclass. Might be <code>null</code>
     * @param withSubClasses whether or not metaclass inheritance should be considered for the check
     * @param stereotype a stereotype. Might be <code>null</code>
     * @param withSubStereotypes whether or not stereotype inheritance should be considered for the check
     * @since 3.8
     */
    @objid ("85750b6d-c45c-4ddc-89a0-24725435c9a5")
    public ElementScope(MClass metaclass, boolean withSubClasses, Stereotype stereotype, boolean withSubStereotypes) {
        assert (metaclass != null || stereotype != null);
        this.metaclass = metaclass;
        this.stereotype = stereotype;
        this.withSubClasses = withSubClasses;
        this.withSubStereotypes = withSubStereotypes;
    }

    @objid ("e7de823a-5181-4c86-94aa-f935f3b17db5")
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
        ElementScope other = (ElementScope) obj;
        return Objects.equals(this.metaclass, other.metaclass) && Objects.equals(this.stereotype, other.stereotype);
    }

    @objid ("db2ac5a6-c615-41f7-8ff3-4649c8f83805")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.metaclass == null) ? 0 : this.metaclass.hashCode());
        result = prime * result + ((this.stereotype == null) ? 0 : this.stereotype.hashCode());
        return result;
    }

    /**
     * Check that the given element matches the scope's metaclass/stereotype.
     * 
     * @param e the element to check.
     * @return <code>true</code> if the element matches the scope's metaclass/stereotype.
     * @since 3.8
     */
    @objid ("ae99ea46-f22f-43f3-8930-46de40c1a8e0")
    public final boolean isMatching(MObject e) {
        return checkMetaclass(e) && checkStereotype(e);
    }

    /**
     * Check that the given element matches the scope's metaclass/stereotype.
     * 
     * @param e the element to check.
     * @param inherit whether or not stereotype inheritance should be considered for the check. This parameter is now obsolete, the one given at the scope's constructor is used instead.
     * @return <code>true</code> if the element matches the scope's metaclass/stereotype.
     * @deprecated use {@link #isMatching(MObject)} instead.
     */
    @objid ("bcfa4672-c3e1-4098-95fc-6fe2cad221cd")
    @Deprecated
    public final boolean isMatching(MObject e, boolean inherit) {
        return isMatching(e);
    }

    @objid ("e879458e-14e7-498c-87d6-08b19ca6acf9")
    private boolean checkMetaclass(MObject e) {
        // No metaclass matches any element
        if (this.metaclass == null) {
            return true;
        }
        if (this.withSubClasses) {
            return e.getMClass().hasBase(this.metaclass);
        } else {
            return e.getMClass().equals(this.metaclass);
        }
    }

    @objid ("ad0d9887-38a7-46ff-89d0-304aee334503")
    private boolean checkStereotype(MObject e) {
        // No stereotype matches any element
        if (this.stereotype == null) {
            return true;
        }
        
        // Check stereotypes
        if (e instanceof ModelElement) {
            for (Stereotype st : ((ModelElement) e).getExtension()) {
                if (compareStereotype(st, this.stereotype)) {
                    return true;
                }
            }
        }
        return false;
    }

    @objid ("e1bf6f02-a636-48e3-bea4-574f01cc37c9")
    private boolean compareStereotype(Stereotype aStereotype, Stereotype type) {
        if (aStereotype.equals(type)) {
            return true;
        } else if (this.withSubStereotypes) {
            Stereotype steParent = aStereotype.getParent();
            if (steParent != null) {
                return compareStereotype(steParent, type);
            }
        }
        return false;
    }

}
