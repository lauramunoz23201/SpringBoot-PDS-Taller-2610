package co.com.poli.springboot_taller1_pds.mapper;

public interface IMapper<I, O>{
    O map(I in);
}
