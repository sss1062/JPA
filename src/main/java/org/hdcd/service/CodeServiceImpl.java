package org.hdcd.service;

import java.util.ArrayList;
import java.util.List;

import org.hdcd.domain.CodeDetail;
import org.hdcd.domain.CodeGroup;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.repository.CodeDetailRepository;
import org.hdcd.repository.CodeGroupRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

	private final CodeGroupRepository repository;

	private final CodeDetailRepository codeDetailRepository;

	@Override
	public List<CodeLabelValue> getGroupCodeList() throws Exception {
		List<CodeGroup> codeGroups = repository.findAll(Sort.by(Direction.ASC, "groupCode"));
		
		List<CodeLabelValue> codeGroupList = new ArrayList<CodeLabelValue>();
		
		for(CodeGroup codeGroup : codeGroups) {
			codeGroupList.add(new CodeLabelValue(codeGroup.getGroupCode(), codeGroup.getGroupName()));
		}
		
		return codeGroupList;
	}
	
	@Override
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
		List<CodeDetail> codeDetails = codeDetailRepository.getCodeList(groupCode);
		
		List<CodeLabelValue> codeList = new ArrayList<CodeLabelValue>();
		
		for(CodeDetail codeDetail : codeDetails) {
			codeList.add(new CodeLabelValue(codeDetail.getCodeValue(), codeDetail.getCodeName()));
		}
		
		return codeList;
	}
	
}
