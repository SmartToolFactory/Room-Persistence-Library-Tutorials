//package com.test.tutorial5_1tablerelations.data;
//
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Transaction;
//
//import com.test.tutorial5_1tablerelations.R;
//
//import java.util.List;
//
///**
// * General Dao class to put 1 to many relation based objects.
// *
// * <p></p>
// * I: type of petId of objects to insert
// * P: Parent object type, user for instance
// * C: Child object type, pets for instance
// */
//@Dao
//public abstract class GeneralDao {
//
//    @Insert
//    public abstract long insertUser(User user);
//
//
//    @Insert
//    public <P extends Identifiable<Long>, C extends Identifiable<Long>> void insertParentWithChildren(P parent, List<C> children) {
//
//      Long petId =  insert(parent);
//
//        for (C child : children) {
//            child.setPetId(parent.getPetId());
//            insert(child);
//        }
//
//    }
//
//    @Insert
//    abstract <T, R> R insert(T row);
//
//
//    interface Identifiable<T> {
//        T getPetId();
//
//        void setPetId(T petId);
//    }
//
//}
