package picstroy.backend.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import picstroy.backend.domain.Member;
import picstroy.backend.domain.Post;
import picstroy.backend.repository.MemberRepository;
import picstroy.backend.repository.PostRepository;
import picstroy.backend.web.dto.CreatePostRequest;
import picstroy.backend.web.dto.PostResponse;
import picstroy.backend.web.dto.UpdatePostRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";

    public List<PostResponse> findMyPosts(HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_MEMBER_ID);

        if(memberId == null) {
            throw new IllegalArgumentException("로그인 후 이용해 주세요");
        }

        return postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional
    public PostResponse create(CreatePostRequest request,  HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_MEMBER_ID);

        if(memberId == null) {
            throw new IllegalArgumentException("로그인 후 이용해 주세요");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Post post = new Post(
                request.category(),
                request.title(),
                request.content(),
                member
        );

        Post savePost = postRepository.save(post);

        return PostResponse.from(savePost);
    }

    @Transactional
    public PostResponse update(Long id, UpdatePostRequest request, HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_MEMBER_ID);

        if(memberId == null) {
            throw new IllegalArgumentException("로그인 후 이용해 주세요");
        }

        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if(!post.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("본인이 작성한 글만 수정 가능");
        }

        post.update(request.category(), request.title(), request.content());

        return PostResponse.from(post);
    }

    @Transactional
    public void delete(Long id, HttpSession session) {
        Long memberId = (Long) session.getAttribute(LOGIN_MEMBER_ID);

        if(memberId == null) {
            throw new IllegalArgumentException("로그인 후 이용해 주세요");
        }

        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if(!post.getMember().getId().equals(memberId)) {
            throw new IllegalArgumentException("본인이 작성한 글만 삭제 가능");
        }

        postRepository.delete(post);
    }
}
