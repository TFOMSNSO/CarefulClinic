package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;

import com.careful.clinic.upload.interfase.IDataUploadType;

public class BreakAgreement extends AbstractDataUploadType{

	public BreakAgreement(OPCPackage pkg, String fileName) throws IOException {
		super.set(pkg, fileName);
	}

	@Override
	public void checkOutStructure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOutTypizine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void constructQuery() throws ParseException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void checkSinchronizeData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkSinchronizeData(List<String> listquery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	IDataUploadType getInstansUploadData() {
		// TODO Auto-generated method stub
		return null;
	}

		@Override
	public String construct_querySelect(String str) {
		StringBuilder  sb = new StringBuilder();
		String tmp_m[] = null, tmp = null;
		tmp = str.replace("insert into", "select  count(*) from");
		sb.append(tmp.substring(0, 34));
		
		tmp_m = tmp.substring(29).split(",");
		sb.append(" where ");
		sb.append("p.fam=");
		sb.append(tmp_m[1]);
		sb.append(" and p.im=");
		 sb.append(tmp_m[2]);
		 sb.append(" and p.ot=");
		 sb.append(tmp_m[3]);
		 sb.append(" and p.dr=");
		 sb.append(tmp_m[4]);
		 sb.append(" and p.d_assent=");
		 sb.append(tmp_m[5]);
		 sb.append(" and p.d_break_assent=");
		 sb.append(tmp_m[6]);
		 sb.append(" and p.id_assent=");
		 sb.append(tmp_m[7]);
		 sb.append(" and p.id_break_assent=");
		 sb.append(tmp_m[8]);
		 sb.append(" and p.smo=");
		 sb.append(tmp_m[9]);
		
		return sb.toString();
	}
}
