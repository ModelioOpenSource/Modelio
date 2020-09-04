/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vstore.exml.common.index.jdbm;

import java.io.IOError;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.StreamException;

/**
 * {@link Spliterator} that transforms {@link IOError} and {@link InternalError} to
 * {@link JdbmIndexException}  wrapped into {@link StreamException}.
 * @author cma
 * @since 3.6
 * 
 * @param <T>
 */
@objid ("cdaf2640-3362-4e42-b783-add8c59e6a0f")
class JdbmSpliterator<T> implements Spliterator<T> {
    @objid ("898a0724-9680-4d4c-96a3-6e46f2884c36")
    private final Spliterator<T> wrapped;

    @objid ("c2f5cc94-3b4d-4f18-89b7-f863a802b2b2")
    public JdbmSpliterator(Spliterator<T> wrapped) {
        this.wrapped = wrapped;
    }

    @objid ("404b064c-15e5-4a3a-b9e0-6d9878ee1690")
    @Override
    public boolean tryAdvance(Consumer<? super T> action) throws StreamException {
        try {
            return this.wrapped.tryAdvance(action);
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("22a9f1c6-4aae-4418-b546-8381d98a7c00")
    @Override
    public Spliterator<T> trySplit() throws StreamException {
        try {
            return new JdbmSpliterator<>(this.wrapped.trySplit());
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("97d56428-8358-49a6-a46b-6bf06ac3fb99")
    @Override
    public long estimateSize() throws StreamException {
        try {
            return this.wrapped.estimateSize();
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("7c0a6c84-7c77-4af9-91d4-14953b5dcadd")
    @Override
    public int characteristics() {
        return this.wrapped.characteristics();
    }

    @objid ("0930d0d9-a18d-4467-a5b6-d6877b899132")
    @Override
    public Comparator<? super T> getComparator() {
        return this.wrapped.getComparator();
    }

    @objid ("8d91a99f-cd80-4bbf-b8c7-9dc9177a3980")
    @Override
    public long getExactSizeIfKnown() throws StreamException {
        try {
            return this.wrapped.getExactSizeIfKnown();
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("7fae1226-0f7e-4776-ae19-a6cfeb9db642")
    private StreamException fail(JdbmIndexException from) {
        return new StreamException(from);
    }

    @objid ("ae1b8c93-ca71-4254-866c-b6fc259d24a8")
    @Override
    public boolean hasCharacteristics(int characteristics) {
        return this.wrapped.hasCharacteristics(characteristics);
    }

}
