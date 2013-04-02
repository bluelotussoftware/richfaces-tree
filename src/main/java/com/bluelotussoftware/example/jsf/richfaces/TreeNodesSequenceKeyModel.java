/*
 * 
 * Copyright 2013 Blue Lotus Software LLC. All Rights Reserved.
 * 
 * $Id$
 * 
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
