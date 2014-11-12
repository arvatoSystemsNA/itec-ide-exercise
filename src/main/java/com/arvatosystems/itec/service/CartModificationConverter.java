package com.arvatosystems.itec.service;

import com.arvatosystems.itec.pojo.CartModificationData;
import com.arvatosystems.itec.pojo.CommerceCartModification;

public interface CartModificationConverter
{

	CartModificationData convert(CommerceCartModification modification);

}
