/*
 * Copyright (C) 2011-2013  Blue Lotus Software, LLC.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
/*
 * $Id$
 */
package com.bluelotussoftware.example.jsf.richfaces;

import java.util.Iterator;
import javax.faces.convert.Converter;
import org.richfaces.convert.StringSequenceRowKeyConverter;
import org.richfaces.model.NodesTreeSequenceKeyModel;
import org.richfaces.model.TreeDataModelTuple;
import org.richfaces.model.iterators.ClassicTreeNodeTuplesIterator;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
public class TreeNodesSequenceKeyModel<V> extends NodesTreeSequenceKeyModel<TypedTreeNode<V>> {

    private static final Converter DEFAULT_CONVERTER = new StringSequenceRowKeyConverter();

    @Override
    protected TypedTreeNode<V> setupChildContext(Object key) {
        return getData().getChild(key);
    }

    @Override
    public Object getWrappedData() {
        return getRootNode();
    }

    @Override
    public void setWrappedData(Object data) {
        setRootNode((TypedTreeNode<V>) data);
    }

    @Override
    public boolean isLeaf() {
        return getData().isLeaf();
    }

    @Override
    public Iterator<TreeDataModelTuple> children() {
        return new ClassicTreeNodeTuplesIterator(getData(), getRowKey());
    }

    @Override
    public Converter getRowKeyConverter() {
        return DEFAULT_CONVERTER;
    }
}
