/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.uno.UnoRuntime;

/**
 * Wrapper base class for UNO services which emulates the upcoming mode of automatic
 * runtime Java classes to get rid of the need for queryInterface.
 * <p>
 * Because its not worth the effort to create a runtime generated wrapper for this purpose,
 * as it might be for OOo 2.0, you still have to use {@link UnoRuntime#queryInterface(Class, Object)}
 * for interfaces which are optional or come from a subclass.
 * But for non optional interfaces you can already directly call their methods.
 * <p>
 * This wrapper will only work for UNO objects via a bridge, not for direct Java objects.
 * 
 * 
 * @since OOo 2.0.0
 */
@objid ("ff4ce722-5d1b-494f-abf9-0041574dc3fb")
class Wrapper implements com.sun.star.lib.uno.Proxy, com.sun.star.uno.IQueryInterface, com.sun.star.lang.XComponent {
    @objid ("5013c4fb-a796-4890-9318-89a76953aa05")
    private com.sun.star.uno.IQueryInterface xQueryInterface;

    @objid ("af4f5704-12d7-4e3b-b603-81e754d45829")
    private com.sun.star.lang.XComponent xComponent;

    @objid ("25ac4214-7bfd-4051-bc9a-d200a002824a")
    public  Wrapper(final com.sun.star.uno.XInterface xProxy) {
        this.xQueryInterface = (com.sun.star.uno.IQueryInterface) xProxy;
        this.xComponent = UnoRuntime.queryInterface( 
                                                    com.sun.star.lang.XComponent.class, xProxy );
        
    }

    /**
     * ==============================================================
     * com.sun.star.uno.IQueryInterface
     * --------------------------------------------------------------
     */
    @objid ("a1263bea-9a29-430a-9e47-18605c1dba09")
    @Override
    public String getOid() {
        return this.xQueryInterface.getOid();
    }

    @objid ("5fbb68ce-882d-4841-a0ee-c6260b2f3e23")
    @Override
    public boolean isSame(final Object aObject) {
        return this.xQueryInterface.isSame( aObject );
    }

    @objid ("4f291581-d7e9-4e67-a257-9dc618a28de1")
    @Override
    public Object queryInterface(final com.sun.star.uno.Type aType) {
        //System.err.println( "Wrapper::queryInterface(" + aType + ")" );
        return this.xQueryInterface.queryInterface( aType );
    }

    /**
     * ==============================================================
     * com.sun.star.lang.XComponent
     * --------------------------------------------------------------
     */
    @objid ("6cb88e59-5373-4699-9627-d54a9e6437a5")
    @Override
    public void dispose() {
        this.xComponent.dispose();
    }

    @objid ("4eda0a74-84f0-4a49-aa38-add186c66e11")
    @Override
    public void addEventListener(final com.sun.star.lang.XEventListener xListener) {
        this.xComponent.addEventListener( xListener );
    }

    @objid ("6905cb7b-6fc7-4742-abc4-801367678ebe")
    @Override
    public void removeEventListener(final com.sun.star.lang.XEventListener xListener) {
        this.xComponent.removeEventListener( xListener );
    }

}
